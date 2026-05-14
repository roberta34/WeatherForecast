import { Link } from "react-router-dom";

export default function Navbar() {

    return (

        <nav className="navbar">

            <h2>Weather Forecast</h2>

            <div className="nav-links">

                <Link to="/">
                    Dashboard
                </Link>

                <Link to="/forecast">
                    Forecast
                </Link>

                <Link to="/statistics">
                    Statistics
                </Link>

            </div>

        </nav>
    );
}