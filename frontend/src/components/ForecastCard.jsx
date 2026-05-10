export default function ForecastCard({ forecast }) {
    return (
      <div className={"forecast-card"}>
          <h3>
              {forecast.date}
          </h3>

          <p>
              Temperatura minimă: {forecast.minTemperature}°C
          </p>

          <p>
              Temperatura maximă: {forecast.maxTemperature}°C
          </p>

          <p>
              Vânt: {forecast.windSpeed} km/h
          </p>

          <p>
              Umiditate: {forecast.humidity}%
          </p>

          <p>
              Vreme: {forecast.weatherType}
          </p>
      </div>
    );
}