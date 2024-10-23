import React, { useState, useEffect } from 'react';
import weatherService from '../services/weatherService';

const CurrentWeather = () => {
  const [weatherReport, setWeatherReport] = useState([]);
  const [loading, setLoading] = useState(true); // Track loading state

  useEffect(() => {
    const fetchWeatherData = async () => {
      try {
        const response = await weatherService.getCurrentWeather(); // Fetch data from the backend
        // Assuming response.data contains the weather data
        const reportLines = response.data.trim().split('\n'); // Split by new lines
        const parsedReport = reportLines.map(line => {
          const match = line.match(/Current temperature in (.+) is ([\d.]+)°C with (.+)\./);
          if (match) {
            return {
              city: match[1].trim(),
              temperature: match[2].trim(),
              condition: capitalizeFirstLetter(match[3].trim()), // Capitalize the first letter of the condition
            };
          }
          return null; // Return null for lines that don't match
        }).filter(item => item); // Filter out any null values
        setWeatherReport(parsedReport); // Set the parsed weather report
      } catch (error) {
        console.error("Error fetching current weather data: ", error);
      } finally {
        setLoading(false); // Set loading to false after fetch attempt
      }
    };

    fetchWeatherData();
  }, []);

  // Function to capitalize the first letter of a string
  const capitalizeFirstLetter = (string) => {
    return string.charAt(0).toUpperCase() + string.slice(1);
  };

  // Return loading message or table based on weather report state
  return (
    <div style={styles.container}>
      <h2 style={styles.header}>Current Weather</h2>
      {loading ? (
        <p>Loading current weather data...</p>
      ) : weatherReport.length > 0 ? (
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.headerCell}>City</th>
              <th style={styles.headerCell}>Temperature (°C)</th>
              <th style={styles.headerCell}>Condition</th>
            </tr>
          </thead>
          <tbody>
            {weatherReport.map((report, index) => (
              <tr key={index}>
                <td style={styles.cell}>{report.city}</td>
                <td style={styles.cell}>{report.temperature}</td>
                <td style={styles.cell}>{report.condition}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No weather data available.</p>
      )}
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
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
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

export default CurrentWeather;
