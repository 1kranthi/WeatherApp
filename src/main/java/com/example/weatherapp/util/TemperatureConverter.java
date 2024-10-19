package com.example.weatherapp.util;

public class TemperatureConverter {

    // Converts temperature from Kelvin to Celsius
    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    // Converts temperature from Kelvin to Fahrenheit
    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }

    // Converts temperature based on user preference (Celsius or Fahrenheit)
    public static double convertTemperature(double kelvin, String preference) {
        if ("F".equalsIgnoreCase(preference)) {
            return kelvinToFahrenheit(kelvin);
        }
        return kelvinToCelsius(kelvin); // Default to Celsius
    }
}

