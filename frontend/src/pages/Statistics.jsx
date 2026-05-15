import {
    useEffect,
    useState
} from "react";

import {
    Line
} from "react-chartjs-2";

import StatCard
    from "../components/StatCard";

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
    getStatistics,
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

export default function Statistics() {

    const [statistics, setStatistics] =
        useState([]);

    const [cities, setCities] =
        useState([]);

    const [error, setError] =
        useState("");

    useEffect(() => {

        loadStatistics();

    }, []);

    async function loadStatistics() {

        try {

            const statisticsResponse =
                await getStatistics();

            const citiesResponse =
                await getCities();

            setStatistics(
                statisticsResponse.data.data
            );

            setCities(
                citiesResponse.data.data
            );

        } catch (err) {

            setError(
                "Failed to load statistics"
            );
        }
    }

    if(error) {

        return <h2>{error}</h2>;
    }

    if(statistics.length === 0) {

        return <h2>Loading statistics...</h2>;
    }

    const data = {

        labels: statistics.map((s) => {

            const city =
                cities.find(
                    (c) => c.id === s.cityId
                );

            return city
                ? city.name
                : `City ${s.cityId}`;
        }),

        datasets: [

            {
                label:
                    "Average Temperature",

                data: statistics.map(
                    (s) =>
                        s.averageTemperature
                ),

                borderColor: "cyan",

                backgroundColor:
                    "cyan",

                tension: 0.4
            }
        ]
    };

    const hottestCity =
        statistics.reduce((max, city) =>
                city.averageTemperature >
                max.averageTemperature
                    ? city
                    : max,
            statistics[0]
        );

    const coldestCity =
        statistics.reduce((min, city) =>
                city.averageTemperature <
                min.averageTemperature
                    ? city
                    : min,
            statistics[0]
        );

    const highestHumidity =
        statistics.reduce((max, city) =>
                city.averageHumidity >
                max.averageHumidity
                    ? city
                    : max,
            statistics[0]
        );

    const strongestWind =
        statistics.reduce((max, city) =>
                city.averageWindSpeed >
                max.averageWindSpeed
                    ? city
                    : max,
            statistics[0]
        );

    function getCityName(cityId) {

        const city =
            cities.find(
                (c) => c.id === cityId
            );

        return city
            ? city.name
            : `City ${cityId}`;
    }

    return (

        <div className="statistics-page">

            <h1>
                Weather Statistics
            </h1>

            <div className="chart-container">

                <Line data={data} />

            </div>

            <div className="stats-grid">

                <StatCard
                    title="Hottest City"
                    value={
                        `${getCityName(
                            hottestCity.cityId
                        )} (${hottestCity.averageTemperature}°C)`
                    }
                />

                <StatCard
                    title="Coldest City"
                    value={
                        `${getCityName(
                            coldestCity.cityId
                        )} (${coldestCity.averageTemperature}°C)`
                    }
                />

                <StatCard
                    title="Highest Humidity"
                    value={
                        `${getCityName(
                            highestHumidity.cityId
                        )} (${highestHumidity.averageHumidity}%)`
                    }
                />

                <StatCard
                    title="Strongest Wind"
                    value={
                        `${getCityName(
                            strongestWind.cityId
                        )} (${strongestWind.averageWindSpeed} km/h)`
                    }
                />


            </div>

            <div className="analysis-box">

                <h2>
                    Weather Analysis
                </h2>

                <p>
                    {getCityName(
                        hottestCity.cityId
                    )} currently records the
                    highest average temperature.
                </p>

                <p>
                    {getCityName(
                        coldestCity.cityId
                    )} remains the coldest city
                    in the dataset.
                </p>

                <p>
                    {getCityName(
                        highestHumidity.cityId
                    )} has the highest humidity.
                </p>

                <p>
                    Strong winds are currently
                    detected in {" "}
                    {getCityName(
                        strongestWind.cityId
                    )}.
                </p>

            </div>

        </div>
    );
}