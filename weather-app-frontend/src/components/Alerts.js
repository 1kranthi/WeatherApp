import React, { useState, useEffect } from 'react';
import weatherService from '../services/weatherService';

const Alerts = () => {
  const [alertMessage, setAlertMessage] = useState('');

  useEffect(() => {
    weatherService.getWeatherAlerts().then((response) => {
      setAlertMessage(response.data);
    }).catch((error) => {
      console.error("Error fetching weather alerts: ", error);
    });
  }, []);

  return (
    <div>
      <h2>Weather Alerts</h2>
      <p>{alertMessage}</p>
    </div>
  );
};

export default Alerts;
