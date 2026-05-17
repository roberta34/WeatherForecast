export default function ForecastCard({
                                         forecast
                                     }) {

    function getWeatherIcon(type) {

        if(type === "sunny") {

            return "☀️";
        }

        if(type === "rain") {

            return "🌧";
        }

        if(type === "snow") {

            return "❄️";
        }

        if(type === "cloudy") {

            return "☁️";
        }

        return "🌍";
    }

    return (

        <div className="forecast-card">

            <h3>
                {forecast.forecastDate}
            </h3>

            <p>
                Min Temperature:
                {" "}
                {forecast.minTemperature}°C
            </p>

            <p>
                Max Temperature:
                {" "}
                {forecast.maxTemperature}°C
            </p>

            <p>
                Wind:
                {" "}
                {forecast.windSpeed} km/h
            </p>

            <p>
                Humidity:
                {" "}
                {forecast.humidity}%
            </p>

            <p>
                Weather:
                {" "}
                {getWeatherIcon(
                    forecast.weatherType
                )}

                {" "}

                {forecast.weatherType}
            </p>

            <p>
                UV Index:
                {" "}
                {forecast.uvIndex}
            </p>

        </div>
    );
}