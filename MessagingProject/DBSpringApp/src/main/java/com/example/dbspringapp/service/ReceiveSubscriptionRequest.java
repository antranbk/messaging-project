package com.example.dbspringapp.service;

import com.example.dbspringapp.configuration.DataConfiguration;
import com.example.dbspringapp.dto.UserSubscriptionRequestDto;
import com.example.dbspringapp.model.Cell;
import com.example.dbspringapp.model.User;
import com.example.dbspringapp.repository.CellRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceiveSubscriptionRequest {
    private CellRepository cellRepository;

    @Autowired
    public ReceiveSubscriptionRequest(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    @RabbitListener(queues = {DataConfiguration.SUB_QUEUE})
    public void receiveSubscriptionRequest(UserSubscriptionRequestDto userSubscriptionRequestDto) {
        Optional<Cell> optionalCell = cellRepository.findCellGivenCoord(userSubscriptionRequestDto.getLatitude(), userSubscriptionRequestDto.getLongitude());
        if (optionalCell.isPresent()) {
            Cell cell = optionalCell.get();
            updateUserList(cell, userSubscriptionRequestDto.getUser());
        }
    }

    private void updateUserList(Cell cell, User user) {
        Optional<User> optionalUser = cell.getUserList().stream()
                .filter(existUser -> existUser.getEmail().equals(user.getEmail()) && existUser.getName().equals(user.getName()))
                .findAny();
        if (optionalUser.isPresent()) {
            cell.getUserList().remove(optionalUser.get());
        }
        cell.getUserList().add(user);
        cellRepository.saveCell(cell);
    }
}
