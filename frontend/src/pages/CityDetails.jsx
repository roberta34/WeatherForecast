import {
    useEffect,
    useState
} from "react";

import {
    useParams
} from "react-router-dom";

import {
    Line
} from "react-chartjs-2";

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Tooltip,
    Legend
} from "chart.js";

import {
    getCityForecast,
    getCities
} from "../services/api";

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Tooltip,
    Legend
);

export default function CityDetails() {

    const { id } = useParams();

    const [forecast, setForecast] =
        useState([]);

    const [cityName, setCityName] =
        useState("");

    const [error, setError] =
        useState("");

    useEffect(() => {

        async function fetchData() {

            try {

                const forecastResponse =
                    await getCityForecast(id);

                const citiesResponse =
                    await getCities();

                setForecast(
                    forecastResponse.data.data
                );

                const city =
                    citiesResponse.data.data.find(
                        (c) => c.id === parseInt(id)
                    );

                if(city) {

                    setCityName(city.name);
                }

            } catch (err) {

                setError(
                    "Failed to load city details."
                );
            }
        }

        fetchData();

    }, [id]);


    if(error) {

        return <h2>{error}</h2>;
    }

    if(forecast.length === 0) {

        return <h2>Loading city data...</h2>;
    }

    const data = {

        labels: forecast.map(
            (f) => f.date
        ),

        datasets: [

            {
                label:
                    "Max Temperature",

                data: forecast.map(
                    (f) => f.temperatureMax
                ),

                borderColor: "cyan",

                backgroundColor:
                    "cyan",

                tension: 0.4
            },

            {
                label:
                    "Min Temperature",

                data: forecast.map(
                    (f) => f.temperatureMin
                ),

                borderColor: "orange",

                backgroundColor:
                    "orange",

                tension: 0.4
            }
        ]
    };

    const latestForecast =
        forecast[forecast.length - 1];

    return (

        <div className="statistics-page">

            <h1>
                {cityName} Weather Details
            </h1>

            <div className="chart-container">

                <Line data={data} />

            </div>

            <div className="stats-grid">

                <div className="stat-card">

                    <h3>
                        Weather Type
                    </h3>

                    <p>
                        <p>

                            {latestForecast.weatherType === "sunny" && "☀️ Sunny"}

                            {latestForecast.weatherType === "rain" && "🌧 Rain"}

                            {latestForecast.weatherType === "snow" && "❄️ Snow"}

                            {latestForecast.weatherType === "cloudy" && "☁️ Cloudy"}

                        </p>
                    </p>

                </div>

                <div className="stat-card">

                    <h3>
                        Humidity
                    </h3>

                    <p>
                        {latestForecast.humidity}%
                    </p>

                </div>

                <div className="stat-card">

                    <h3>
                        Wind Speed
                    </h3>

                    <p>
                        {latestForecast.windSpeed} km/h
                    </p>

                </div>

                <div className="stat-card">

                    <h3>
                        UV Index
                    </h3>

                    <p>
                        {latestForecast.uvIndex}
                    </p>

                </div>

            </div>

        </div>
    );
}