import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/weather';

const getCurrentWeather = () => {
  return axios.get(`${BASE_URL}/current`);
};

const getWeatherSummaries = () => {
  return axios.get(`${BASE_URL}/summary`);
};

const getWeatherAlerts = () => {
  return axios.get(`${BASE_URL}/alerts`);
};

export default {
  getCurrentWeather,
  getWeatherSummaries,
  getWeatherAlerts
};
