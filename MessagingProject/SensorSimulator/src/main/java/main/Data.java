package main;

import java.util.Random;

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

    public Data() {
        this.precipitationType = generatePrecipitationType();
        this.precipitationTypeAmount = generatePrecipitationTypeAmount();
        this.temperature = generateTemperature();
        this.windSpeed = generateWindSpeed();
        this.humidity = generateHumidity();
    }

    public PrecipitationType getPrecipitationType() {
        return precipitationType;
    }

    public int getPrecipitationTypeAmount() {
        return precipitationTypeAmount;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setPrecipitationType(PrecipitationType precipitationType) {
        this.precipitationType = precipitationType;
    }

    public void setPrecipitationTypeAmount(int precipitationTypeAmount) {
        this.precipitationTypeAmount = precipitationTypeAmount;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    private PrecipitationType generatePrecipitationType() {
        return PrecipitationType.values()[new Random().nextInt(PrecipitationType.values().length)];
    }

    private int generatePrecipitationTypeAmount() {//0 to 9
        return (int) (Math.random() * Data.precipitationTypeMaxAmount);
    }

    private double generateTemperature() {//-50 to 49.99
        return ((Math.random() * 100) - 50);
    }

    private int generateWindSpeed() {//0 to 20
        return (int) (Math.random() * Data.maxWindSpeed);
    }

    private int generateHumidity() {//0 to 100
        return (int) (Math.random() * Data.maxHumidity);
    }

}
