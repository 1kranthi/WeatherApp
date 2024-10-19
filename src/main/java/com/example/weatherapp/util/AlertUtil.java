package com.example.weatherapp.util;

public class AlertUtil {

    public static boolean checkTemperatureThreshold(double currentTemp) {
        double threshold = 35.0; // Example threshold
        return currentTemp > threshold;
    }
}
