export default function ForecastCard({ forecast }) {
    return (
      <div className={"forecast-card"}>
          <h3>
              {forecast.date}
          </h3>

          <p>
              Min Temperature: {forecast.minTemperature}°C
          </p>

          <p>
              Max Temperature: {forecast.maxTemperature}°C
          </p>

          <p>
              Wind: {forecast.windSpeed} km/h
          </p>

          <p>
              Humidity: {forecast.humidity}%
          </p>

          <p>
              Weather: {forecast.weatherType}
          </p>
      </div>
    );
}