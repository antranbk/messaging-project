package com.example.httpendpoint.dto;

import com.example.httpendpoint.model.Data;
import com.example.httpendpoint.model.Threshold;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorDto {
    private int id;
    private Data sensorData;
    private double latitude;
    private double longitude;
    private LocalDateTime timeStamp;
    private Threshold threshold;
}
