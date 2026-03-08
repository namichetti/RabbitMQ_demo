package com.amichetti.rabbintmq.publisher;

import com.amichetti.rabbintmq.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMQProducer {

    @Value("${spring.exchange}")
    private String exchange;

    @Value("${spring.routing.key}")
    private String routingKey;

    @Value("${spring.json.exchange}")
    private String jsonExchange;

    @Value("${spring.routing.json.key}")
    private String jsonRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){
        log.info("Sending message: {}", message);
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void sendMessage(User user){
        log.info("Sending message: {}", user.toString());
        this.rabbitTemplate.convertAndSend(jsonExchange, jsonRoutingKey, user);
    }
}
