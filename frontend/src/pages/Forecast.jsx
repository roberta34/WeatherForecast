import { useEffect, useState } from "react";

import {
    getCities,
    getForecastByCity
} from "../services/api";

import ForecastCard from "../components/ForecastCard";
import Loading from "../components/Loading";
import ErrorMessage from "../components/ErrorMessage";

export default function Forecast() {
    const [cities, setCities] = useState([]);

    const [selectedCity, setSelectedCity] = useState("");

    const [forecasts, setForecasts] = useState([]);

    const [loading, setLoading] = useState(false);

    const [error, setError] = useState("");

    useEffect(() => {
        loadCities();
    }, []);

    async function loadCities() {
        try {
            const response = await getCities();
            setCities(response.data.data);
        } catch (err) {
            setError("Failed to load cities.");
        }
    }

    async function handleCityChange(event) {
        const cityId = event.target.value;

        setSelectedCity(cityId);

        if(!cityId) {
            return;
        }

        try {
            setLoading(true);

            setError("");

            const response =
                await getForecastByCity(cityId);

            setForecasts(response.data.data);
        } catch (err) {
            setError("Failed to load forecasts.");
        } finally {
            setLoading(false);
        }
    }

    return(
        <div className="forecast-page">
            <h1>Weather Forecast</h1>

            <select
                value={selectedCity}
                onChange={handleCityChange}
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
                    )
                )}
            </select>

            {loading && <Loading />}

            {error && (
                <ErrorMessage message={error} />
            )}

            <div className="forecast-list">

                {forecasts.map((forecast) => (
                    <ForecastCard
                        key={forecast.id}
                        forecast={forecast}
                    />
                    )
                )}

            </div>
        </div>
    );
}