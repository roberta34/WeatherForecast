
CREATE OR REPLACE FUNCTION avg_temperature(
    p_city_id INT
)
    RETURNS FLOAT AS $$
DECLARE
    avg_temp FLOAT;
BEGIN

    SELECT AVG((temperature_min + temperature_max)/2)
    INTO avg_temp
    FROM weather_forecasts
    WHERE city_id = p_city_id;

    RETURN avg_temp;

END;
$$ LANGUAGE plpgsql;

--calculam indexul UV
CREATE OR REPLACE FUNCTION calculate_uv(
    temp FLOAT,
    humidity FLOAT,
    wind_speed FLOAT,
    cloud_cover FLOAT,
    season TEXT
)
RETURNS  FLOAT AS $$
DECLARE
    uv FLOAT;
    season_bonus FLOAT := 0;
BEGIN

    IF season = 'SUMMER' THEN
        season_bonus := 3;
    ELSIF season = 'SPRING' THEN
        season_bonus := 1.5;
    ELSIF season = 'AUTUMN' THEN
        season_bonus := 0.5;
    ELSE
        season_bonus := -1;
    END IF;

    uv :=   temp * 0.12 +
            (100 - humidity) * 0.03 +
            wind_speed * 0.02 +
            (100 - cloud_cover) * 0.15 +
            season_bonus;

    IF uv < 0 THEN
        uv := 0;
    END IF;

    IF uv > 11 THEN
        uv := 11;
    END IF;

    RETURN ROUND(uv::NUMERIC, 2);

END;
$$ LANGUAGE plpgsql;


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


---comparam date vechi
CREATE OR REPLACE FUNCTION compare_temperatures(p_city_id INT)
RETURNS TABLE(forecast_date DATE, avg_temp FLOAT) AS $$
BEGIN
    RETURN QUERY
    SELECT wf.forecast_date, (temperature_min + temperature_max)/2
    FROM weather_forecasts AS wf
    WHERE wf.city_id = p_city_id
    ORDER BY wf.forecast_date;
END;
$$ LANGUAGE plpgsql;


---temperatura media pe o perioada
CREATE OR REPLACE FUNCTION get_average_temperature(
    p_city_id INT,
    p_start_date DATE,
    p_end_date DATE
)
RETURNS NUMERIC(5,2)
AS $$
DECLARE
    result NUMERIC(5,2);
BEGIN
    SELECT AVG((temperature_min + temperature_max) / 2.0)
    INTO result
    FROM weather_forecasts
    WHERE city_id = p_city_id
     AND forecast_date BETWEEN p_start_date AND p_end_date;
    RETURN  result;
END;
$$ LANGUAGE plpgsql;


---min/max

CREATE OR REPLACE FUNCTION get_temperature_extremes(
    p_city_id INT,
    p_start_date DATE,
    p_end_date DATE
)
RETURNS TABLE(
    min_temperature NUMERIC,
    max_temperature NUMERIC
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        MIN(temperature_min),
        MAX(temperature_max)
    FROM weather_forecasts
    WHERE city_id = p_city_id
    AND forecast_date BETWEEN p_start_date AND p_end_date;
end;
$$ LANGUAGE plpgsql;


---functia de ranking orase
CREATE  OR REPLACE FUNCTION rank_cities_by_average_temperature(
    p_start_date DATE,
    p_end_date DATE
)
RETURNS TABLE(
    city_id INT,
    city_name TEXT,
    average_temperature NUMERIC
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        c.id,
        c.name,
        AVG((wf.temperature_min + wf.temperature_max) / 2.0) AS average_temperature
    FROM cities c
    JOIN weather_forecasts wf
    ON c.id = wf.city_id
    WHERE wf.forecast_date BETWEEN p_start_date AND p_end_date
    GROUP BY c.id, c.name
    ORDER BY average_temperature DESC;

END;
$$LANGUAGE plpgsql;




---procedura pt statistici orase
CREATE OR REPLACE PROCEDURE generate_city_statistics()
AS $$
BEGIN

    DELETE FROM city_statistics;

    INSERT INTO city_statistics (
        city_id,
        average_temperature,
        min_temperature,
        max_temperature,
        average_humidity,
        average_wind_speed,
        generated_at
    )

    SELECT
        city_id,
        AVG((temperature_min + temperature_max) / 2.0),
        MIN(temperature_min),
        MAX(temperature_max),
        AVG(humidity),
        AVG(wind_speed),
        NOW()

    FROM weather_forecasts

    GROUP BY city_id;

END;
$$ LANGUAGE plpgsql;


---procedura pt analiza sezoniera
CREATE OR REPLACE PROCEDURE generate_seasonal_analysis()
AS $$
BEGIN

    DELETE FROM seasonal_analysis;

    INSERT INTO seasonal_analysis (
        city_id,
        season,
        average_temperature,
        min_temperature,
        max_temperature,
        average_humidity
    )
    SELECT
        city_id,

        CASE
            WHEN EXTRACT(MONTH FROM forecast_date) IN (12, 1, 2) THEN 'WINTER'
            WHEN EXTRACT(MONTH FROM forecast_date) IN (3, 4, 5) THEN 'SPRING'
            WHEN EXTRACT(MONTH FROM forecast_date) IN (6, 7, 8) THEN 'SUMMER'
            ELSE 'AUTUMN'
            END AS season,

        AVG((temperature_min + temperature_max) / 2.0),

        MIN(temperature_min),

        MAX(temperature_max),

        AVG(humidity)

    FROM weather_forecasts

    GROUP BY
        city_id,

        CASE
            WHEN EXTRACT(MONTH FROM forecast_date) IN (12, 1, 2) THEN 'WINTER'
            WHEN EXTRACT(MONTH FROM forecast_date) IN (3, 4, 5) THEN 'SPRING'
            WHEN EXTRACT(MONTH FROM forecast_date) IN (6, 7, 8) THEN 'SUMMER'
            ELSE 'AUTUMN'
            END;

END;
$$ LANGUAGE plpgsql;

---procedura pt ranking orase
CREATE OR REPLACE PROCEDURE generate_city_rankings()
AS $$
BEGIN
    DELETE FROM city_rankings;

    INSERT INTO city_rankings (city_id,
                               ranking_type,
                               ranking_position,
                               score
    )
    SELECT
        city_id,
        'HOTTEST',
        RANK() OVER (ORDER BY AVG((temperature_min + temperature_max) / 2.0) DESC),
        AVG((temperature_min + temperature_max) / 2.0)
    FROM weather_forecasts
    GROUP BY city_id;

END;
$$ LANGUAGE plpgsql;


---procedura pt anomalii
CREATE OR REPLACE PROCEDURE detect_weather_anomalies()
AS $$
BEGIN
    DELETE FROM weather_anomalies;

    INSERT INTO weather_anomalies (city_id,
                                   forecast_id,
                                   anomaly_type,
                                   description
    )
    SELECT
        city_id,
        id,
        CASE
            WHEN temperature_max >= 40 THEN 'EXTREME HEAT'
            WHEN temperature_min <= -15 THEN 'EXTREME COLD'
            WHEN wind_speed >= 80 THEN 'HIGH_WIND'
            WHEN humidity >= 90 THEN 'HIGH_HUMIDITY'
        END,
        CASE
            WHEN temperature_max >= 40 THEN 'Temperature really high'
            WHEN temperature_min <= -15 THEN 'Temperature really low'
            WHEN wind_speed >= 80 THEN 'Strong wind'
            WHEN humidity >= 90 THEN 'Humidity really high'
        END
    FROM weather_forecasts
    WHERE temperature_max >= 40
    OR temperature_min <= -15
    OR wind_speed >= 80
    OR humidity >= 90;
END;
$$ LANGUAGE plpgsql;
