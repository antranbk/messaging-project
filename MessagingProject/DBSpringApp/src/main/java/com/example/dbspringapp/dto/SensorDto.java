package com.example.dbspringapp.dto;

import com.example.dbspringapp.model.Data;
import com.example.dbspringapp.model.Threshold;
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
