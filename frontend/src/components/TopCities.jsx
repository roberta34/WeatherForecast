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

                    <h3>{city.name}</h3>

                    <p>
                        {city.temperature}°C
                    </p>

                </div>

            ))}

        </div>
    );
}