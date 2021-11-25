package com.example.dbspringapp.service;

import com.example.dbspringapp.configuration.DataConfiguration;
import com.example.dbspringapp.model.Cell;
import com.example.dbspringapp.dto.SensorDto;
import com.example.dbspringapp.dto.UserSubscribeDataDto;
import com.example.dbspringapp.repository.CellRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Component
public class ReceiveSensorData {

    private CellRepository cellRepository;
    private ModelMapper modelMapper;
    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public ReceiveSensorData(CellRepository cellRepository, ModelMapper modelMapper, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.cellRepository = cellRepository;
        this.modelMapper = modelMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.findAndRegisterModules();
    }

    @RabbitListener(queues = {DataConfiguration.DATA_QUEUE})
    public void receiveSensorData(SensorDto sensor) {
        processData(sensor);

    }

    private void processData(SensorDto sensor) { //assume there are only 25 cell  0 ....4/5...9/...../20....24
        Optional<Cell> optionalCell = cellRepository.findCellGivenCoord(sensor.getLatitude(), sensor.getLongitude());
        if (optionalCell.isPresent()) {
            Cell cell = optionalCell.get();
            updateSensorInCell(cell, sensor);
            cellRepository.saveCell(cell);
            if (isThresholdCross(sensor)) {
                sendUserDataToHttpEndpoint(cell, sensor);
            }
        }


    }

    private boolean isThresholdCross(SensorDto sensor) {
        boolean result = false;
        if (sensor.getSensorData().getPrecipitationTypeAmount() > sensor.getThreshold().getPrecipitationTypeAmount() ||
                sensor.getSensorData().getTemperature() > sensor.getThreshold().getTemperature() ||
                sensor.getSensorData().getHumidity() > sensor.getThreshold().getHumidity() ||
                sensor.getSensorData().getWindSpeed() > sensor.getThreshold().getWindSpeed()) {
            result = true;
        }
        return result;
    }

    private void updateSensorInCell(Cell cell, SensorDto sensor) {
        Optional<SensorDto> optionalSensor = cell.getSensorList().stream()
                .filter(sensorInList -> sensorInList.getId() == sensor.getId())
                .findAny();
        if (optionalSensor.isPresent()) {
            cell.getSensorList().remove(optionalSensor.get());
        }
        cell.getSensorList().add(sensor);
    }

    private void sendUserDataToHttpEndpoint(Cell cell, SensorDto sensor) {
        if (cell.getUserList().size() > 0) {
            UserSubscribeDataDto userSubscribeData = new UserSubscribeDataDto(cell.getUserList(), sensor.getSensorData(), sensor.getTimeStamp());
            try {
                sendPostRequest("http://localhost:8090/userdata", objectMapper.writeValueAsString(userSubscribeData));
            } catch (IOException e) {
                System.out.println("can't send user data to http endpoint");
            }
        }

    }

    public static void sendPostRequest(String sendUrl, String jsonBody) throws IOException {
        URL url = new URL(sendUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
