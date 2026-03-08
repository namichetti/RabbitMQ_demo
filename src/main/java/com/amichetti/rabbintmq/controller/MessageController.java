package com.amichetti.rabbintmq.controller;

import com.amichetti.rabbintmq.dto.User;
import com.amichetti.rabbintmq.publisher.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final RabbitMQProducer rabbitMQProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> publishMessage(
            @RequestParam String message) {

        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message published");
    }

    @PostMapping("/json/publish")
    public ResponseEntity<String> publishJsonMessage(
            @RequestBody User user) {

        rabbitMQProducer.sendMessage(user);
        return ResponseEntity.ok("Message published");
    }


}
