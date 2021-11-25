package com.example.emailspringapp.dto;

import com.example.emailspringapp.model.Data;
import com.example.emailspringapp.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserSubscribeDataDto {
    private List<User> user;
    private Data data;
    private LocalDateTime timeStamp;

    public UserSubscribeDataDto(List<User> user, Data data, LocalDateTime timeStamp) {
        this.user = user;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "\n humidity: " + this.data.getHumidity() + ", precipitationType:  " + this.data.getPrecipitationType()
                + ", precipitationTypeAmount: " + this.data.getPrecipitationTypeAmount() + ", windspeed: " + this.data.getWindSpeed()
                + ", temperature: " + this.data.getTemperature() + " taken at " + this.timeStamp.toString();
    }
}
