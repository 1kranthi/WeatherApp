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
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherSummaryRepository summaryRepository;
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String API_KEY = "d40c1bc5796dc0ea3a6164e7222073e4"; // Replace with your actual API key
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    // List of cities to fetch weather for
    private final List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad");

    public WeatherService(RestTemplate restTemplate, WeatherSummaryRepository summaryRepository) {
        this.restTemplate = restTemplate;
        this.summaryRepository = summaryRepository;
    }

    // Fetch weather data for all cities
    public String getCurrentWeather() {
        StringBuilder weatherReport = new StringBuilder(); // To store the weather data for all cities

        // Loop through each city to fetch its weather data
        for (String city : cities) {
            String url = API_URL + city + "&appid=" + API_KEY + "&units=metric"; // Fetch in Celsius units

            try {
                // Make the API call and store the response
                String response = restTemplate.getForObject(url, String.class);

                // Parse the JSON response using ObjectMapper
                JsonNode jsonResponse = objectMapper.readTree(response);
                double temperatureCelsius = jsonResponse.get("main").get("temp").asDouble(); // Get temperature
                String weatherCondition = jsonResponse.get("weather").get(0).get("description").asText(); // Get weather condition

                // Append the weather data for the current city to the report
                weatherReport.append("Current temperature in ").append(city)
                        .append(" is ").append(temperatureCelsius)
                        .append("°C with ").append(weatherCondition)
                        .append(".\n");

            } catch (IOException e) {
                // Handle the exception and add an error message for the specific city
                weatherReport.append("Could not retrieve weather data for ").append(city)
                        .append(" at this time.\n");
                System.err.println("Error while parsing weather data for city: " + city + " - " + e.getMessage());
            }
        }

        return weatherReport.toString(); // Return the report containing weather data for all cities
    }

    // Retrieve daily weather summaries from the repository
    public List<WeatherSummary> getDailySummaries() {
        return summaryRepository.findAll();
    }

    // Generate and save a daily weather summary
    public void generateDailySummary(String city, double avgTemp, double maxTemp, double minTemp, String dominantCondition) {
        WeatherSummary summary = new WeatherSummary(null, city, avgTemp, maxTemp, minTemp, dominantCondition, LocalDate.now());
        summaryRepository.save(summary);
    }

    // Scheduled method to fetch and store weather data every 5 minutes for all cities
    @Scheduled(fixedRate = 300000) // 300000 milliseconds = 5 minutes
    public void fetchAndStoreDailyWeatherSummary() {
        for (String city : cities) {
            String url = API_URL + city + "&appid=" + API_KEY + "&units=metric";

            try {
                // Make the API call and store the response
                String response = restTemplate.getForObject(url, String.class);
                JsonNode jsonResponse = objectMapper.readTree(response);

                // Extract relevant weather information
                double avgTemp = jsonResponse.get("main").get("temp").asDouble();
                double maxTemp = jsonResponse.get("main").get("temp_max").asDouble();
                double minTemp = jsonResponse.get("main").get("temp_min").asDouble();
                String dominantCondition = jsonResponse.get("weather").get(0).get("description").asText();

                // Generate and save the weather summary for the city
                generateDailySummary(city, avgTemp, maxTemp, minTemp, dominantCondition);

            } catch (IOException e) {
                System.err.println("Error while fetching and storing weather data for city: " + city + " - " + e.getMessage());
            }
        }
    }
}
