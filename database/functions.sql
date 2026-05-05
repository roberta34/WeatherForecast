---calculam temperatura medie

CREATE OR REPLACE FUNCTION avg_temperature(city INT)
RETURNS FLOAT AS $$
DECLARE
    avg_temp FLOAT;
BEGIN
    SELECT AVG((temperature_min + temperature_max)/2)
    INTO avg_temp
    FROM weather_forecasts
    WHERE city_id = city;

    RETURN avg_temp;
END;
$$ LANGUAGE plpgsql;

--calculam indexul UV
CREATE OR REPLACE FUNCTION calculate_uv(temp FLOAT, humidity FLOAT)
RETURNS  FLOAT AS $$
BEGIN
    RETURN temp * 0.1 + (100 - humidity) * 0.05;
end;
$$ LANGUAGE plpgsql;

--detectam anomalii
--anomalie inseamna ca temperatura e prea mare sau prea mica

CREATE OR REPLACE FUNCTION detect_anomaly(temp FLOAT)
RETURNS BOOLEAN AS $$
BEGIN
    IF temp > 40 OR temp < -20 THEN
        RETURN TRUE;
    END IF;

    RETURN FALSE;
end;
$$ LANGUAGE plpgsql;


---generam prognoza pe 7 zile
CREATE OR REPLACE PROCEDURE generate_forecast(city INT)
LANGUAGE plpgsql
AS $$
DECLARE
    i INT;
BEGIN
    FOR i IN 1..7 LOOP
        INSERT INTO weather_forecasts
        (city_id, date, temperature_min, temperature_max, humidity, wind_speed, uv_index, weather_type)
        VALUES
        (
         city,
         CURRENT_DATE + i,
        10 + random()*10,
         20 + random()*10,
         50 + random()*30,
         5 + random()*10,
         random()*10,
         'generated'
        );
    END LOOP;
END;
$$ LANGUAGE plpgsql;

---comparam date vechi
CREATE OR REPLACE FUNCTION compare_temperatures(city INT)
RETURNS TABLE(date DATE, avg_temp FLOAT) AS $$
BEGIN
    RETURN QUERY
    SELECT date, (temperature_min + temperature_max)/2
    FROM weather_forecasts
    WHERE city_id = city
    ORDER BY date;
END;
$$ LANGUAGE plpgsql;