import React, { useState, useEffect } from 'react';
import weatherService from '../services/weatherService';

const WeatherSummary = () => {
  const [summaries, setSummaries] = useState([]);

  useEffect(() => {
    weatherService.getWeatherSummaries()
      .then((response) => {
        setSummaries(response.data);
      })
      .catch((error) => {
        console.error("Error fetching weather summaries: ", error);
      });
  }, []);

  // Function to capitalize the first letter of a string
  const capitalizeFirstLetter = (string) => {
    return string.charAt(0).toUpperCase() + string.slice(1);
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.header}>Daily Weather Summaries</h2>
      <table style={styles.table}>
        <thead>
          <tr>
            <th style={styles.headerCell}>City</th>
            <th style={styles.headerCell}>Average Temp (°C)</th>
            <th style={styles.headerCell}>Max Temp (°C)</th>
            <th style={styles.headerCell}>Min Temp (°C)</th>
            <th style={styles.headerCell}>Dominant Condition</th>
            <th style={styles.headerCell}>Date</th>
          </tr>
        </thead>
        <tbody>
          {summaries.map((summary, index) => (
            <tr key={index}>
              <td style={styles.cell}>{summary.city}</td>
              <td style={styles.cell}>{summary.avgTemp}</td>
              <td style={styles.cell}>{summary.maxTemp}</td>
              <td style={styles.cell}>{summary.minTemp}</td>
              <td style={styles.cell}>{capitalizeFirstLetter(summary.dominantCondition)}</td>
              <td style={styles.cell}>{summary.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

// Define styles for the component
const styles = {
  container: {
    height: '100vh', // Full height of the viewport
    padding: '20px',
    backgroundColor: '#e6f7ff', // Light blue background for contrast
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
  table: {
    width: '100%',
    height: 'calc(100% - 60px)', // Adjust for header and padding
    borderCollapse: 'collapse',
    marginTop: '0', // Remove margin at the top
    border: '1px solid #0056b3', // Border for the entire table
  },
  headerCell: {
    backgroundColor: '#0056b3', // Header background color
    color: 'white',
    padding: '12px',
    textAlign: 'left',
    border: '1px solid #0056b3', // Border for header cells
  },
  cell: {
    border: '1px solid #0056b3', // Border for data cells
    padding: '12px',
    textAlign: 'left',
    backgroundColor: '#ffffff', // White background for cells
    transition: 'background-color 0.3s', // Smooth background color transition
  },
};

export default WeatherSummary;
