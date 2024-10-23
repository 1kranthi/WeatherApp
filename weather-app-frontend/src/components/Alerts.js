import React, { useState, useEffect } from 'react';
import weatherService from '../services/weatherService';

const Alerts = () => {
  const [alertMessage, setAlertMessage] = useState('');

  useEffect(() => {
    weatherService.getWeatherAlerts()
      .then((response) => {
        setAlertMessage(response.data);
      })
      .catch((error) => {
        console.error("Error fetching weather alerts: ", error);
      });
  }, []);

  return (
    <div style={styles.container}>
      <h2 style={styles.header}>Weather Alerts</h2>
      <p style={styles.alertMessage}>{alertMessage || 'No alerts at the moment.'}</p>
    </div>
  );
};

// Define styles for the component
const styles = {
  container: {
    height: '100vh', // Full height of the viewport
    padding: '20px',
    backgroundColor: '#f0f8ff', // Light blue background for contrast
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center', // Center the content vertically
    borderRadius: '0', // Remove rounded corners for full screen
    boxShadow: 'none', // Remove shadow for full screen
    fontFamily: 'Arial, sans-serif',
  },
  header: {
    color: '#0056b3', // Dark blue color for the header
    textAlign: 'center', // Center the header
    marginBottom: '20px', // Space below the header
    fontSize: '2em', // Larger font size for better visibility
  },
  alertMessage: {
    color: '#333', // Dark gray color for the alert message
    textAlign: 'center', // Center the alert message
    fontSize: '1.5em', // Slightly larger font size for emphasis
    padding: '10px',
    backgroundColor: '#fff', // White background for the message
    border: '1px solid #0056b3', // Border around the message
    borderRadius: '5px', // Slightly rounded corners for the message
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', // Subtle shadow for depth
    maxWidth: '600px', // Max width for better readability
    width: '100%', // Full width
  },
};

export default Alerts;
