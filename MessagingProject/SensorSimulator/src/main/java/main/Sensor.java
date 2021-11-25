package main;

import java.time.LocalDateTime;

public class Sensor {
    private int id;
    private Data sensorData;
    private double latitude;
    private double longitude;
    private LocalDateTime timeStamp;
    private Threshold threshold;

    public Sensor() {
        this.sensorData = new Data();
        this.timeStamp = LocalDateTime.now();
        this.threshold = new Threshold();
    }

    public Sensor(int id, double latitude, double longitude) {
        this();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Sensor(int id, double latitude, double longitude, Threshold threshold) {
        this(id, latitude, longitude);
        this.threshold = threshold;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public int getId() {
        return id;
    }

    public Data getSensorData() {
        return sensorData;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSensorData(Data sensorData) {
        this.sensorData = sensorData;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }
}
