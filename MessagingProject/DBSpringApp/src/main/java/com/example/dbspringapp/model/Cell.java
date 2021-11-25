package com.example.dbspringapp.model;

import com.example.dbspringapp.dto.SensorDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Cell {
    @Id
    private Integer id;

    private List<SensorDto> sensorList;

    private List<User> userList;

    private List<Double> latitude; // from to
    private List<Double> longitude;

    public Cell(Integer id, List<Double> latitude, List<Double> longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sensorList = new ArrayList<>();
        this.userList = new ArrayList<>();
    }
}
