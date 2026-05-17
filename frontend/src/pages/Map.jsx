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

    function getStatisticsForCity(cityId) {

        return statistics.find(
            (s) => s.cityId === cityId
        );
    }

    if(loading) {

        return <h2>Loading map...</h2>;
    }

    if(error) {

        return <h2>{error}</h2>;
    }

    console.log(cities);
    console.log(statistics);
    return (


        <div className="map-page">

            <h1>
                Weather Map
            </h1>

            <MapContainer
                center={[50.1109, 8.6821]}
                zoom={4}
                scrollWheelZoom={true}
                style={{
                    height: "700px",
                    width: "100%"
                }}
            >

                <TileLayer
                    attribution='&copy; OpenStreetMap contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                {cities
                    .filter((city) =>
                        city.latitude !== null &&
                        city.longitude !== null &&
                        city.latitude !== undefined &&
                        city.longitude !== undefined
                    )
                    .map((city) => {

                        const stats =
                            getStatisticsForCity(
                                city.id
                            );

                        return (

                            <Marker
                                key={city.id}
                                position={[
                                    parseFloat(city.latitude),
                                    parseFloat(city.longitude)
                                ]}
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