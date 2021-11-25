package com.example.httpendpoint.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {
    public static final String DATA_QUEUE = "sensor_data_queue";
    public static final String USER_QUEUE = "user_data_queue";
    public static final String SUB_QUEUE = "sub_queue";

    public static final String EXCHANGE_NAME = "data_exchange";

    public static final String SENSOR_ROUTING_KEY = "sensor_data";
    public static final String USER_ROUTING_KEY = "user_data";
    public static final String SUB_ROUTING_KEY = "sub_data";

    @Bean
    Queue dataQueue() {
        return new Queue(DATA_QUEUE, false);
    }

    @Bean
    Queue userQueue() {
        return new Queue(USER_QUEUE, false);
    }

    @Bean
    Queue subQueue() {
        return new Queue(SUB_QUEUE, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding1(DirectExchange exchange) {
        return BindingBuilder.bind(dataQueue()).to(exchange).with(SENSOR_ROUTING_KEY);
    }

    @Bean
    Binding binding2(DirectExchange exchange) {
        return BindingBuilder.bind(userQueue()).to(exchange).with(USER_ROUTING_KEY);
    }

    @Bean
    Binding binding3(DirectExchange exchange) {
        return BindingBuilder.bind(subQueue()).to(exchange).with(SUB_ROUTING_KEY);
    }

    @Bean
    public ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {

        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter(objectMapper));
        return rabbitTemplate;

    }


}
