import React, { useState, useEffect } from 'react';
import weatherService from '../services/weatherService';

const CurrentWeather = () => {
  const [weatherReport, setWeatherReport] = useState('');

  useEffect(() => {
    weatherService.getCurrentWeather().then((response) => {
      setWeatherReport(response.data);
    }).catch((error) => {
      console.error("Error fetching current weather data: ", error);
    });
  }, []);

  return (
    <div>
      <h2>Current Weather</h2>
      <pre>{weatherReport}</pre>
    </div>
  );
};

export default CurrentWeather;
