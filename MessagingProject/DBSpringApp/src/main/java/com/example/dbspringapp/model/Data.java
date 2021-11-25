package com.example.dbspringapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    public static final int precipitationTypeMaxAmount = 10;
    public static final double maxTemperature = 50.0;
    public static final int maxWindSpeed = 21;
    public static final int maxHumidity = 101;
    private PrecipitationType precipitationType;
    private int precipitationTypeAmount;
    private double temperature;
    private int windSpeed;
    private int humidity;
}
