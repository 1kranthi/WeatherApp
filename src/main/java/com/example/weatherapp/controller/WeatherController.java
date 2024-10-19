package com.example.weatherapp.controller;

import com.example.weatherapp.model.WeatherSummary;
import com.example.weatherapp.service.WeatherService;
import com.example.weatherapp.service.AlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final AlertService alertService;

    public WeatherController(WeatherService weatherService, AlertService alertService) {
        this.weatherService = weatherService;
        this.alertService = alertService;
    }

    @GetMapping("/current")
    public String getCurrentWeather() {
        return weatherService.getCurrentWeather();
    }

    @GetMapping("/summary")
    public List<WeatherSummary> getDailySummaries() {
        return weatherService.getDailySummaries();
    }

    @GetMapping("/alerts")
    public String checkForAlerts() {
        return alertService.checkForAlerts();
    }
}
