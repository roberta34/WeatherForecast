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