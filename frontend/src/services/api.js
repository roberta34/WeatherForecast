import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8085/api"
});

export const getCities = () => API.get("/cities");

export const getForecastByCity = (cityId) =>
    API.get(`/forecast/${cityId}`);