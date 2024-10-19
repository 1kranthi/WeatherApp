package com.example.weatherapp.service;

import com.example.weatherapp.util.AlertUtil;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    public String checkForAlerts() {
        // Check if the temperature exceeds a threshold
        boolean alertTriggered = AlertUtil.checkTemperatureThreshold(35.0);
        return alertTriggered ? "Alert! High temperature detected." : "No alerts.";
    }
}
