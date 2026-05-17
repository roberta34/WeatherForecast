import { useEffect, useState } from "react";

import { Link } from "react-router-dom";

import {
    getCities,
    getForecastByCity
} from "../services/api";

import ForecastCard
    from "../components/ForecastCard";

import Loading
    from "../components/Loading";

import ErrorMessage
    from "../components/ErrorMessage";

export default function Forecast() {

    const [cities, setCities] =
        useState([]);

    const [selectedCity, setSelectedCity] =
        useState("");

    const [forecasts, setForecasts] =
        useState([]);

    const [loading, setLoading] =
        useState(false);

    const [error, setError] =
        useState("");

    useEffect(() => {

        loadCities();

    }, []);

    async function loadCities() {

        try {

            const response =
                await getCities();

            setCities(
                response.data.data
            );

        } catch (err) {

            setError(
                "Failed to load cities."
            );
        }
    }

    async function handleCityChange(event) {

        const cityId =
            event.target.value;

        setSelectedCity(cityId);

        if (!cityId) {

            setForecasts([]);

            return;
        }

        try {

            setLoading(true);

            setError("");

            const response =
                await getForecastByCity(cityId);

            setForecasts(
                response.data.data
            );

        } catch (err) {

            setError(
                "Failed to load forecasts."
            );

        } finally {

            setLoading(false);
        }
    }

    return(

        <div className="forecast-page">

            <div className="forecast-header">

                <h1>
                    Weather Forecast
                </h1>

                <p>
                    Explore detailed weather forecasts for major European cities.
                </p>

            </div>

            <div className="forecast-controls">

                <select
                    value={selectedCity}
                    onChange={handleCityChange}
                    className="city-select"
                >

                    <option value="">
                        Select a city
                    </option>

                    {cities.map((city) => (

                        <option
                            key={city.id}
                            value={city.id}
                        >
                            {city.name}
                        </option>

                    ))}

                </select>

                {selectedCity && (

                    <Link
                        to={`/city/${selectedCity}`}
                        className="details-button"
                    >
                        View City Details
                    </Link>
                )}

            </div>

            {loading && <Loading />}

            {error && (

                <ErrorMessage
                    message={error}
                />
            )}

            <div className="forecast-grid">

                {forecasts.map((forecast) => (

                    <ForecastCard
                        key={forecast.id}
                        forecast={forecast}
                    />

                ))}

            </div>

        </div>
    );
}