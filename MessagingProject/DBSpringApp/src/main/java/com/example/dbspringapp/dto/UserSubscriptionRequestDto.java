package com.example.dbspringapp.dto;

import com.example.dbspringapp.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSubscriptionRequestDto {
    private double latitude;
    private double longitude;
    private User user;
}
