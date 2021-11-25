package main;

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
}
