package com.example.emailspringapp.service;

import com.example.emailspringapp.configuration.DataConfiguration;
import com.example.emailspringapp.dto.UserSubscribeDataDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserDataService {
    @RabbitListener(queues = {DataConfiguration.USER_QUEUE})
    public void readUserData(UserSubscribeDataDto userSubscribeData) {
        userSubscribeData.getUser().stream()
                .forEach(user -> System.out.println("send sensor data to " + user.getEmail() + userSubscribeData));


    }
    
}
