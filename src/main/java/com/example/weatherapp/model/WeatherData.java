package com.example.weatherapp.model;

public class WeatherData {

    private double temp;
    private double tempMin;
    private double tempMax;
    private String mainCondition;

    
    public WeatherData() {
    }
    
    public WeatherData(double temp, double tempMin, double tempMax, String mainCondition) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.mainCondition = mainCondition;
    }
    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }
    public double getTempMin() {
        return tempMin;
    }
    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }
    public double getTempMax() {
        return tempMax;
    }
    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
    public String getMainCondition() {
        return mainCondition;
    }
    public void setMainCondition(String mainCondition) {
        this.mainCondition = mainCondition;
    }

    
}
