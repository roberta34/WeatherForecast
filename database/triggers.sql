---generare alerta cand se depasesc 35 de grade celsius
CREATE OR REPLACE FUNCTION auto_alert()
RETURNS TRIGGER AS $$
    BEGIN
        IF NEW.temperature_max >= 40 THEN
            INSERT INTO weather_alerts (city_id, alert_type, description, severity)
            VALUES (NEW.city_id, 'heat', 'High temperature detected', 'high');
        ELSEIF NEW.temperature_max >= 35 THEN
            INSERT INTO weather_alerts (city_id, alert_type, description, severity, created_at)
            VALUES (
                    NEW.city_id,
                    'HEAT',
                    'High temperature detected',
                    'high'
                   );
        END IF;

        IF NEW.temperature_min <= -15 THEN
            INSERT INTO weather_alerts (id, city_id, alert_type, description, severity, created_at)
            VALUES (
                    NEW.city_id,
                    'EXTREME_COLD',
                    'Extreme cold detected',
                    'critical'
                   );

        END IF;

        IF NEW.wind_speed >= 80 THEN
            INSERT INTO weather_alerts(city_id, alert_type, description, severity, created_at)
            VALUES (
                    NEW.city_id,
                    'HIGH_WIND',
                    'Strong wind detected',
                    'high'
                   );
        END IF;

        IF NEW.humidity >= 90 THEN

            INSERT INTO weather_alerts (
                city_id,
                alert_type,
                description,
                severity
            )
            VALUES (
                       NEW.city_id,
                       'HIGH_HUMIDITY',
                       'Humidity level very high',
                       'medium'
                   );

        END IF;
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_auto_alert
AFTER INSERT ON weather_forecasts
FOR EACH ROW
EXECUTE FUNCTION auto_alert();

CREATE OR REPLACE FUNCTION log_update()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO forecast_logs (
                               forecast_id,
                               operation_type,
                               old_temperature_min,
                               new_temperature_min,
                               old_temperature_max,
                               new_temperature_max,
                               old_humidity,
                               new_humidity,
                               old_wind_speed,
                               new_wind_speed)
    VALUES (
            NEW.id,
            'UPDATE',
            OLD.temperature_min,
            NEW.temperature_min,

            OLD.temperature_max,
            NEW.temperature_max,

            OLD.humidity,
            NEW.humidity,

            OLD.wind_speed,
            NEW.wind_speed
           );

    RETURN NEW;
end;
$$ LANGUAGE  plpgsql;

CREATE TRIGGER trigger_log_update
AFTER UPDATE ON weather_forecasts
FOR EACH ROW
EXECUTE FUNCTION log_update();

CREATE OR REPLACE FUNCTION auto_calculate_uv()
    RETURNS TRIGGER AS $$
BEGIN

    NEW.uv_index :=
            calculate_uv(
                    (NEW.temperature_min + NEW.temperature_max)/2,
                    NEW.humidity,
                    NEW.wind_speed,
                    20,
                    'SUMMER'
            );

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_auto_uv
    BEFORE INSERT ON weather_forecasts
    FOR EACH ROW
EXECUTE FUNCTION auto_calculate_uv();

CREATE OR REPLACE FUNCTION detect_anomaly_trigger()
    RETURNS TRIGGER AS $$
BEGIN

    IF NEW.temperature_max > 50 THEN

        INSERT INTO weather_anomalies (
            forecast_id,
            city_id,
            anomaly_type,
            description
        )
        VALUES (
                   NEW.id,
                   NEW.city_id,
                   'INVALID_HEAT',
                   'Temperature too high'
               );

    END IF;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_detect_anomaly
    AFTER INSERT ON weather_forecasts
    FOR EACH ROW
EXECUTE FUNCTION detect_anomaly_trigger();