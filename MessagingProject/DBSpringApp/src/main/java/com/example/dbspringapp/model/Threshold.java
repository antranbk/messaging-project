package com.example.dbspringapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Threshold {
    private int precipitationTypeAmount;
    private double temperature;
    private int windSpeed;
    private int humidity;

    public Threshold(int precipitationTypeAmount, double temperature, int windSpeed, int humidity) {
        this.precipitationTypeAmount = precipitationTypeAmount;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
    }

    public Threshold() {
        this.precipitationTypeAmount = Data.precipitationTypeMaxAmount;
        this.temperature = Data.maxTemperature;
        this.windSpeed = Data.maxWindSpeed;
        this.humidity = Data.maxHumidity;
    }
}
