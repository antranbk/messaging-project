package com.example.httpendpoint.dto;

import com.example.httpendpoint.model.Data;
import com.example.httpendpoint.model.User;
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
}
