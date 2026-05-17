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

            const cityIds = [
                1,2,3,4,5,
                6,7,8,9,10,
                11,12,13,14,15
            ];

            const alertsPromises =
                cityIds.map((id) => getAlerts(id));

            const alertsResponses =
                await Promise.all(alertsPromises);

            const allAlerts =
                alertsResponses.flatMap(
                    (response) => response.data.data
                );

            console.log(allAlerts);

            const citiesResponse =
                await getTopCities();

            setOverview(
                overviewResponse.data.data[0]
            );

            setAlerts(allAlerts);

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
                        message={
                            alert.message
                        }
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