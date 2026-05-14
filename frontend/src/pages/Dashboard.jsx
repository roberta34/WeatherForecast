import { useEffect, useState } from "react";

import StatCard from "../components/StatCard";
import AlertCard from "../components/AlertCard";
import TopCities from "../components/TopCities";

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
                overviewResponse.data.data
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

        return <h2>{error}</h2>;
    }

    if (!overview) {

        return <h2>Loading...</h2>;
    }

    return (

        <div className="dashboard dashboard-page">

            <h1>Weather Dashboard</h1>

            <div className="stats-grid">

                <StatCard
                    title="Average Temp"
                    value={
                        overview.averageTemperature
                    }
                />

                <StatCard
                    title="Hottest City"
                    value={
                        overview.hottestCity
                    }
                />

                <StatCard
                    title="Coldest City"
                    value={
                        overview.coldestCity
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