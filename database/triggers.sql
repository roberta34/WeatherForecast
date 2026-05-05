---generare alerta cand se depasesc 35 de grade celsius
CREATE OR REPLACE FUNCTION auto_alert()
RETURNS TRIGGER AS $$
    BEGIN
        IF NEW.temperature_max > 35 THEN
            INSERT INTO weather_alerts (city_id, alert_type, description, severity)
            VALUES (NEW.city_id, 'heat', 'High temperature detected', 'high');
        END IF;

        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_auto_alert
AFTER INSERT ON weather_forecasts
FOR EACH ROW
EXECUTE FUNCTION auto_alert();

CREATE TABLE forecast_logs (
  id SERIAL PRIMARY KEY,
  forecast_id INT,
  change_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE OR REPLACE FUNCTION log_update()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO forecast_logs (forecast_id)
    VALUES (NEW.id);

    RETURN NEW;
end;
$$ LANGUAGE  plpgsql;

CREATE TRIGGER trigger_log_update
AFTER UPDATE ON weather_forecasts
FOR EACH ROW
EXECUTE FUNCTION log_update();