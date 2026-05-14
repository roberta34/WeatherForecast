import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8085/api"
});

export const getCities = () => API.get("/cities");

export const getForecastByCity = (cityId) =>
    API.get(`/forecast/${cityId}`);

export const getOverview = () =>
    API.get("/statistics/overview");

export const getAlerts = (cityId) =>
    API.get(`/alerts/${cityId}`);

export const getTopCities = () =>
    API.get("/ranking");