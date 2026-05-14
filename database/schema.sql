---Countries

CREATE TABLE countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(10) UNIQUE
);

---Cities
CREATE TABLE cities(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_id INT NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    population INT,

    CONSTRAINT fk_country
                   FOREIGN KEY (country_id)
                   REFERENCES countries(id)
                   ON DELETE CASCADE
);

---Users
CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

---Weather forecasts

CREATE TABLE weather_forecasts (
    id SERIAL PRIMARY KEY,
    city_id INT NOT NULL,
    date DATE NOT NULL,
    temperature_min FLOAT,
    temperature_max FLOAT,
    humidity FLOAT,
    wind_speed FLOAT,
    uv_index FLOAT,
    weather_type VARCHAR(50),

    CONSTRAINT fk_city_forecast
                                FOREIGN KEY (city_id)
                               REFERENCES cities(id)
                               ON DELETE CASCADE,
    CONSTRAINT unique_city_date
                               UNIQUE (city_id, date)
);


---Weather alerts

CREATE TABLE weather_alerts (
    id SERIAL PRIMARY KEY,
    city_id INT NOT NULL,
    alert_type VARCHAR(50),
    description TEXT,
    severity VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_city_alert
                            FOREIGN KEY (city_id)
                            REFERENCES cities(id)
                            ON DELETE CASCADE
);


CREATE TABLE city_statistics (
                                 id SERIAL PRIMARY KEY,
                                 city_id INT NOT NULL REFERENCES cities(id),
                                 average_temperature NUMERIC(5,2),
                                 min_temperature NUMERIC(5,2),
                                 max_temperature NUMERIC(5,2),
                                 average_humidity NUMERIC(5,2),
                                 average_wind_speed NUMERIC(5,2),
                                 generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE seasonal_analysis (
                                   id SERIAL PRIMARY KEY,
                                   city_id INT NOT NULL REFERENCES cities(id),
                                   season VARCHAR(20) NOT NULL,
                                   average_temperature NUMERIC(5,2),
                                   min_temperature NUMERIC(5,2),
                                   max_temperature NUMERIC(5,2),
                                   average_humidity NUMERIC(5,2),
                                   generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE city_rankings (
                               id SERIAL PRIMARY KEY,
                               city_id INT NOT NULL REFERENCES cities(id),
                               ranking_type VARCHAR(50) NOT NULL,
                               ranking_position INT NOT NULL,
                               score NUMERIC(6,2),
                               generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE weather_anomalies (
                                   id SERIAL PRIMARY KEY,

                                   city_id INT NOT NULL,

                                   forecast_id INT,

                                   anomaly_type VARCHAR(50),

                                   description TEXT,

                                   detected_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                   CONSTRAINT fk_anomaly_city
                                       FOREIGN KEY (city_id)
                                           REFERENCES cities(id)
                                           ON DELETE CASCADE,

                                   CONSTRAINT fk_anomaly_forecast
                                       FOREIGN KEY (forecast_id)
                                           REFERENCES weather_forecasts(id)
                                           ON DELETE CASCADE
);

CREATE TABLE ratings (

                         id SERIAL PRIMARY KEY,

                         user_id INT NOT NULL,

                         city_id INT NOT NULL,

                         rating_value INT NOT NULL CHECK (rating_value BETWEEN 1 AND 5),

                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT fk_rating_user
                             FOREIGN KEY (user_id)
                                 REFERENCES users(id)
                                 ON DELETE CASCADE,

                         CONSTRAINT fk_rating_city
                             FOREIGN KEY (city_id)
                                 REFERENCES cities(id)
                                 ON DELETE CASCADE
);

CREATE TABLE comments
(
    id           SERIAL PRIMARY KEY,
    user_id      INT  NOT NULL,
    city_id      INT  NOT NULL,
    comment_text TEXT NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_comment_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);


CREATE TABLE forecast_logs (
                               id SERIAL PRIMARY KEY,

                               forecast_id INT,

                               operation_type TEXT,

                               old_temperature_min FLOAT,
                               new_temperature_min FLOAT,

                               old_temperature_max FLOAT,
                               new_temperature_max FLOAT,

                               old_humidity FLOAT,
                               new_humidity FLOAT,

                               old_wind_speed FLOAT,
                               new_wind_speed FLOAT,

                               changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE weather_forecasts
    RENAME COLUMN date TO forecast_date;

UPDATE weather_forecasts SET temperature_max = 41 WHERE city_id = 1;