import {
    useEffect,
    useState
} from "react";

import {
    MapContainer,
    TileLayer,
    Marker,
    Popup
} from "react-leaflet";

import L from "leaflet";

import "leaflet/dist/leaflet.css";

import markerIcon2x
    from "leaflet/dist/images/marker-icon-2x.png";

import markerIcon
    from "leaflet/dist/images/marker-icon.png";

import markerShadow
    from "leaflet/dist/images/marker-shadow.png";

import {
    getCities,
    getStatistics
} from "../services/api";

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({

    iconRetinaUrl:
    markerIcon2x,

    iconUrl:
    markerIcon,

    shadowUrl:
    markerShadow,
});

export default function MapPage() {

    const [cities, setCities] =
        useState([]);

    const [statistics, setStatistics] =
        useState([]);

    const [loading, setLoading] =
        useState(true);

    const [error, setError] =
        useState("");

    useEffect(() => {

        loadMapData();

    }, []);

    async function loadMapData() {

        try {

            const citiesResponse =
                await getCities();

            const statisticsResponse =
                await getStatistics();

            setCities(
                citiesResponse.data.data
            );

            setStatistics(
                statisticsResponse.data.data
            );

        } catch (err) {

            setError(
                "Failed to load map data."
            );

        } finally {

            setLoading(false);
        }
    }

    if (loading) {

        return <h2>Loading map...</h2>;
    }

    if (error) {

        return <h2>{error}</h2>;
    }

    return (

        <div className="map-page">

            <h1>
                Weather Map
            </h1>

            <MapContainer
                center={[50, 10]}
                zoom={4}
                scrollWheelZoom={true}
                style={{
                    height: "700px",
                    width: "100%",
                    borderRadius: "20px"
                }}
            >

                <TileLayer
                    attribution='&copy; OpenStreetMap contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                {cities
                    .filter((city) =>
                        city.latitude &&
                        city.longitude
                    )
                    .map((city) => {

                        const stats =
                            statistics.find(
                                (s) => s.cityId === city.id
                            );

                        return (

                            <Marker
                                key={city.id}
                                position={[
                                    Number(city.latitude),
                                    Number(city.longitude)
                                ]}

                                icon={L.divIcon({

                                    className:
                                        "custom-weather-marker",

                                    html: `
                                        <div class="weather-marker">

                                            <div class="temp-badge">
                                                ${stats?.averageTemperature
                                        ? `${stats.averageTemperature}°C`
                                        : "N/A"}
                                            </div>

                                            <div class="city-label">
                                                ${city.name}
                                            </div>

                                        </div>
                                    `,

                                    iconSize: [120, 60],
                                    iconAnchor: [60, 30]
                                })}
                            >

                                <Popup>

                                    <h3>
                                        {city.name}
                                    </h3>

                                    {stats ? (

                                        <div>

                                            <p>
                                                🌡 Avg Temp:
                                                {" "}
                                                {stats.averageTemperature}°C
                                            </p>

                                            <p>
                                                ❄️ Min Temp:
                                                {" "}
                                                {stats.minTemperature}°C
                                            </p>

                                            <p>
                                                ☀️ Max Temp:
                                                {" "}
                                                {stats.maxTemperature}°C
                                            </p>

                                        </div>

                                    ) : (

                                        <p>
                                            No weather data available.
                                        </p>
                                    )}

                                </Popup>

                            </Marker>
                        );
                    })}

            </MapContainer>

        </div>
    );
}