package com.example.httpendpoint.dto;

import com.example.httpendpoint.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSubscriptionRequestDto {
    private double latitude;
    private double longitude;
    private User user;
}
