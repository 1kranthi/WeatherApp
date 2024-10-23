package com.example.weatherapp.service;

import com.example.weatherapp.model.WeatherSummary;
import com.example.weatherapp.repository.WeatherSummaryRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherSummaryRepository summaryRepository;
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String API_KEY = "d40c1bc5796dc0ea3a6164e7222073e4"; // Replace with your actual API key
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    public WeatherService(RestTemplate restTemplate, WeatherSummaryRepository summaryRepository) {
        this.restTemplate = restTemplate;
        this.summaryRepository = summaryRepository;
    }

    public String getCurrentWeather() {
        String city = "Delhi"; 
        String url = API_URL + city + "&appid=" + API_KEY + "&units=metric"; // Fetch in Celsius units

        try {
            // Make the API call and store the response
            String response = restTemplate.getForObject(url, String.class);

            // Parse the JSON response using ObjectMapper
            JsonNode jsonResponse = objectMapper.readTree(response);
            double temperatureCelsius = jsonResponse.get("main").get("temp").asDouble(); // Get temperature
            String weatherCondition = jsonResponse.get("weather").get(0).get("description").asText(); // Get weather condition

            return "Current temperature in " + city + " is " + temperatureCelsius + "°C with " + weatherCondition;

        } catch (IOException e) {
            // Handle the exception by logging it and returning a fallback message
            System.err.println("Error while parsing weather data: " + e.getMessage());
            return "Could not retrieve weather data at this time. Please try again later.";
        }
    }

    public List<WeatherSummary> getDailySummaries() {
        return summaryRepository.findAll();
    }

    // Scheduled method to fetch and store daily weather summary
    @Scheduled(cron = "0 0 8 * * ?") // Runs every day at 8 AM
    public void fetchAndStoreDailyWeatherSummary() {
        String city = "Delhi"; 
        String url = API_URL + city + "&appid=" + API_KEY + "&units=metric"; // Fetch in Celsius units

        try {
            // Make the API call and store the response
            String response = restTemplate.getForObject(url, String.class);

            // Parse the JSON response using ObjectMapper
            JsonNode jsonResponse = objectMapper.readTree(response);
            double temperatureCelsius = jsonResponse.get("main").get("temp").asDouble(); // Get current temperature
            double maxTemp = jsonResponse.get("main").get("temp_max").asDouble(); // Get maximum temperature
            double minTemp = jsonResponse.get("main").get("temp_min").asDouble(); // Get minimum temperature
            String dominantCondition = jsonResponse.get("weather").get(0).get("description").asText(); // Get weather condition
            LocalDate today = LocalDate.now();

            // Save the daily summary to the repository
            WeatherSummary summary = new WeatherSummary(null, city, temperatureCelsius, maxTemp, minTemp, dominantCondition, today);
            summaryRepository.save(summary);
            System.out.println("Daily weather summary saved for " + today);

        } catch (IOException e) {
            // Handle the exception by logging it
            System.err.println("Error while fetching or saving weather data: " + e.getMessage());
        }
    }
}
