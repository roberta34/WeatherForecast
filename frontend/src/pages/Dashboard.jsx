import { useEffect, useState } from "react";

import StatCard from "../components/StatCard";
import AlertCard from "../components/AlertCard";
import TopCities from "../components/TopCities";
import Loading from "../components/Loading";
import ErrorMessage from "../components/ErrorMessage";

import {
    getOverview,
    getAlerts,
    getTopCities
} from "../services/api";

export default function Dashboard() {

    const [overview, setOverview] =
        useState(null);

    const [alerts, setAlerts] =
        useState([]);

    const [topCities, setTopCities] =
        useState([]);

    const [error, setError] =
        useState("");

    useEffect(() => {

        loadDashboard();

    }, []);

    async function loadDashboard() {

        try {

            const overviewResponse =
                await getOverview();

            const alertsResponse =
                await getAlerts(1);

            const citiesResponse =
                await getTopCities();

            setOverview(
                overviewResponse.data.data[0]
            );

            setAlerts(
                alertsResponse.data.data
            );

            setTopCities(
                citiesResponse.data.data
            );

        } catch (err) {

            setError(
                "Failed to load dashboard."
            );
        }
    }

    if (error) {

        return (
            <ErrorMessage
                message={error}
            />
        );
    }

    if (!overview) {

        return <Loading />;
    }

    return (

        <div className="dashboard dashboard-page">

            <div className="dashboard-hero">

                <h1>
                    Weather Forecast Dashboard
                </h1>

                <p>
                    Real-time weather statistics,
                    alerts and city rankings.
                </p>

            </div>

            <div className="stats-grid">

                <StatCard
                    title="Average Temp"
                    value={
                        `${overview.averageTemperature} °C`
                    }
                />

                <StatCard
                    title="Max Temperature"
                    value={
                        `${overview.maxTemperature} °C`
                    }
                />

                <StatCard
                    title="Min Temperature"
                    value={
                        `${overview.minTemperature} °C`
                    }
                />

            </div>

            <div className="alerts-section">

                <h2>Weather Alerts</h2>

                {alerts.map((alert) => (

                    <AlertCard
                        key={alert.id}
                        message={alert.message}
                    />

                ))}

            </div>

            <div className="top-cities-section">

                <h2>Top Cities</h2>

                <TopCities
                    cities={topCities}
                />

            </div>

        </div>
    );
}