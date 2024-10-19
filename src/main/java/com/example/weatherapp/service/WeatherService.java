package com.example.weatherapp.service;

import com.example.weatherapp.model.WeatherSummary;
import com.example.weatherapp.repository.WeatherSummaryRepository;
import com.example.weatherapp.util.TemperatureConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherSummaryRepository summaryRepository;
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String API_KEY = "YOUR_API_KEY";

    public WeatherService(RestTemplate restTemplate, WeatherSummaryRepository summaryRepository) {
        this.restTemplate = restTemplate;
        this.summaryRepository = summaryRepository;
    }

    public String getCurrentWeather() {
        String city = "Delhi"; // You can change this to a dynamic value
        String url = API_URL + city + "&appid=" + API_KEY;
        String response = restTemplate.getForObject(url, String.class);

        // Process weather data here and convert temperature from Kelvin to Celsius
        double temperatureKelvin = 298.15; // Simulated value
        double temperatureCelsius = TemperatureConverter.kelvinToCelsius(temperatureKelvin);
        
        return "Current temperature in " + city + " is " + temperatureCelsius + "°C";
    }

    public List<WeatherSummary> getDailySummaries() {
        return summaryRepository.findAll(); // Fetch daily summaries from the database
    }
}

