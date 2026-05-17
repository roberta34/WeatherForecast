export default function TopCities({
                                      cities
                                  }) {

    return (

        <div className="top-cities">

            {cities.map((city) => (

                <div
                    key={city.id}
                    className="city-card"
                >

                    <h3>
                        {city.rankingPosition}
                        {". "}
                        {city.cityName}
                    </h3>

                    <p>
                        {city.score}°C
                    </p>

                </div>

            ))}

        </div>
    );
}