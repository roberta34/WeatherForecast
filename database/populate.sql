---countries

INSERT INTO countries(name, code) VALUES
('Romania', 'RO'),
('France', 'FR'),
('Germany', 'DE'),
('Italy', 'IT'),
('Spain', 'ES'),
('Netherlands', 'NL'),
('Belgium', 'BE'),
('Austria', 'AT'),
('Poland', 'PL'),
('Czech Republic', 'CZ'),
('Hungary', 'HU'),
('Portugal', 'PT'),
('Sweden', 'SE'),
('Norway', 'NO'),
('Denmark', 'DK');

---cities
INSERT INTO cities (name, country_id, latitude, longitude, population) VALUES
                                                                           ('Bucharest', 1, 44.43, 26.10, 1800000),
                                                                           ('Paris', 2, 48.85, 2.35, 2100000),
                                                                           ('Berlin', 3, 52.52, 13.40, 3600000),
                                                                           ('Rome', 4, 41.90, 12.49, 2800000),
                                                                           ('Madrid', 5, 40.41, -3.70, 3200000),
                                                                           ('Amsterdam', 6, 52.37, 4.90, 800000),
                                                                           ('Brussels', 7, 50.85, 4.35, 1200000),
                                                                           ('Vienna', 8, 48.20, 16.37, 1900000),
                                                                           ('Warsaw', 9, 52.23, 21.01, 1700000),
                                                                           ('Prague', 10, 50.08, 14.43, 1300000),
                                                                           ('Budapest', 11, 47.49, 19.04, 1750000),
                                                                           ('Lisbon', 12, 38.72, -9.13, 500000),
                                                                           ('Stockholm', 13, 59.32, 18.06, 975000),
                                                                           ('Oslo', 14, 59.91, 10.75, 700000),
                                                                           ('Copenhagen', 15, 55.67, 12.56, 800000);

---users
INSERT INTO users (username, email, password_hash) VALUES
                                                       ('user1', 'user1@mail.com', 'pass'),
                                                       ('user2', 'user2@mail.com', 'pass'),
                                                       ('user3', 'user3@mail.com', 'pass'),
                                                       ('user4', 'user4@mail.com', 'pass'),
                                                       ('user5', 'user5@mail.com', 'pass'),
                                                       ('user6', 'user6@mail.com', 'pass'),
                                                       ('user7', 'user7@mail.com', 'pass'),
                                                       ('user8', 'user8@mail.com', 'pass'),
                                                       ('user9', 'user9@mail.com', 'pass'),
                                                       ('user10', 'user10@mail.com', 'pass'),
                                                       ('user11', 'user11@mail.com', 'pass'),
                                                       ('user12', 'user12@mail.com', 'pass'),
                                                       ('user13', 'user13@mail.com', 'pass'),
                                                       ('user14', 'user14@mail.com', 'pass'),
                                                       ('user15', 'user15@mail.com', 'pass');


---weather forecasts
INSERT INTO weather_forecasts
(city_id, forecast_date, temperature_min, temperature_max, humidity, wind_speed, uv_index, weather_type)
VALUES
    (1, '2025-05-01', 10, 20, 60, 10, 5, 'sunny'),
    (2, '2025-05-01', 12, 22, 65, 8, 6, 'cloudy'),
    (3, '2025-05-01', 8, 18, 70, 12, 4, 'rain'),
    (4, '2025-05-01', 15, 25, 55, 7, 7, 'sunny'),
    (5, '2025-05-01', 14, 24, 50, 9, 8, 'sunny'),
    (6, '2025-05-01', 11, 19, 75, 11, 5, 'cloudy'),
    (7, '2025-05-01', 10, 17, 80, 13, 4, 'rain'),
    (8, '2025-05-01', 9, 18, 68, 10, 5, 'cloudy'),
    (9, '2025-05-01', 7, 16, 72, 14, 3, 'rain'),
    (10, '2025-05-01', 8, 17, 69, 12, 4, 'cloudy'),
    (11, '2025-05-01', 10, 20, 66, 9, 6, 'sunny'),
    (12, '2025-05-01', 13, 23, 60, 8, 7, 'sunny'),
    (13, '2025-05-01', 6, 15, 78, 15, 3, 'rain'),
    (14, '2025-05-01', 5, 14, 80, 16, 2, 'snow'),
    (15, '2025-05-01', 7, 16, 75, 13, 3, 'cloudy');


---weather alerts
INSERT INTO weather_alerts (city_id, alert_type, description, severity) VALUES
                                                                            (1, 'heat', 'High temperature warning', 'medium'),
                                                                            (2, 'wind', 'Strong winds expected', 'high'),
                                                                            (3, 'rain', 'Heavy rain alert', 'high'),
                                                                            (4, 'uv', 'High UV index', 'medium'),
                                                                            (5, 'heat', 'Extreme heat', 'high'),
                                                                            (6, 'fog', 'Low visibility', 'low'),
                                                                            (7, 'storm', 'Storm incoming', 'high'),
                                                                            (8, 'snow', 'Snowfall warning', 'medium'),
                                                                            (9, 'rain', 'Flood risk', 'high'),
                                                                            (10, 'wind', 'Wind gusts', 'medium'),
                                                                            (11, 'heat', 'Hot weather', 'low'),
                                                                            (12, 'uv', 'UV risk', 'medium'),
                                                                            (13, 'snow', 'Heavy snow', 'high'),
                                                                            (14, 'ice', 'Ice on roads', 'high'),
                                                                            (15, 'rain', 'Persistent rain', 'medium');