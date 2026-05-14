import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import Navbar from "./components/Navbar";

import Dashboard from "./pages/Dashboard";
import Forecast from "./pages/Forecast";
import Statistics from "./pages/Statistics";
import CityDetails from "./pages/CityDetails";

import "./App.css";

function App() {

    return (

        <BrowserRouter>

            <Navbar />

            <Routes>

                <Route
                    path="/"
                    element={<Dashboard />}
                />

                <Route
                    path="/forecast"
                    element={<Forecast />}
                />

                <Route
                    path="/statistics"
                    element={<Statistics />}
                />

                <Route
                    path="/city/:id"
                    element={<CityDetails />}
                />

            </Routes>

        </BrowserRouter>
    );
}

export default App;