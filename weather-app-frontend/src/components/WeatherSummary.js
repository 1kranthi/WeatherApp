import React, { useState, useEffect } from 'react';
import weatherService from '../services/weatherService';

const WeatherSummary = () => {
  const [summaries, setSummaries] = useState([]);

  useEffect(() => {
    weatherService.getWeatherSummaries().then((response) => {
      setSummaries(response.data);
    }).catch((error) => {
      console.error("Error fetching weather summaries: ", error);
    });
  }, []);

  return (
    <div>
      <h2>Daily Weather Summaries</h2>
      <table>
        <thead>
          <tr>
            <th>City</th>
            <th>Average Temp</th>
            <th>Max Temp</th>
            <th>Min Temp</th>
            <th>Dominant Condition</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {summaries.map((summary, index) => (
            <tr key={index}>
              <td>{summary.city}</td>
              <td>{summary.avgTemp}</td>
              <td>{summary.maxTemp}</td>
              <td>{summary.minTemp}</td>
              <td>{summary.dominantCondition}</td>
              <td>{summary.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default WeatherSummary;
