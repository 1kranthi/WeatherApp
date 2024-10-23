package com.example.weatherapp.util;

public class TemperatureConverter {

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }

    public static double convertTemperature(double kelvin, String preference) {
        if ("F".equalsIgnoreCase(preference)) {
            return kelvinToFahrenheit(kelvin);
        }
        return kelvinToCelsius(kelvin); 
    }
}
