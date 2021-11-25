package com.example.httpendpoint.controller;

import com.example.httpendpoint.configuration.DataConfiguration;
import com.example.httpendpoint.dto.UserSubscriptionRequestDto;
import com.example.httpendpoint.dto.SensorDto;
import com.example.httpendpoint.model.User;
import com.example.httpendpoint.dto.UserSubscribeDataDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorController {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SensorController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/sensordata")
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorDto sensor) {
        rabbitTemplate.convertAndSend(DataConfiguration.EXCHANGE_NAME, DataConfiguration.SENSOR_ROUTING_KEY, sensor);
        return new ResponseEntity<>("put sensor data in queue successfully", HttpStatus.CREATED);
    }

    @PostMapping("/userdata")
    public ResponseEntity<String> receiveUserData(@RequestBody UserSubscribeDataDto userSubscribeData) {
        rabbitTemplate.convertAndSend(DataConfiguration.EXCHANGE_NAME, DataConfiguration.USER_ROUTING_KEY, userSubscribeData);
        return new ResponseEntity<>("put userdata in queue successfully", HttpStatus.CREATED);

    }

    @PostMapping("/cell/subscribe/{latitude}/{longitude}")
    public ResponseEntity<String> addUserSubscription(@PathVariable double latitude, @PathVariable double longitude, @RequestBody User user) {
        UserSubscriptionRequestDto userSubscriptionRequestDto = new UserSubscriptionRequestDto(latitude, longitude, user);
        rabbitTemplate.convertAndSend(DataConfiguration.EXCHANGE_NAME, DataConfiguration.SUB_ROUTING_KEY, userSubscriptionRequestDto);
        return new ResponseEntity<>("subscription request accepted", HttpStatus.CREATED);
    }


}
