package com.amichetti.rabbintmq.consumer;

import com.amichetti.rabbintmq.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMQConsumer {

    @RabbitListener(queues ={"${spring.queue}"} )
    public void consume(String message){
        log.info("Received Message: {}", message);

    }

    @RabbitListener(queues ={"${spring.json.queue}"} )
    public void jsonConsume(User user){
        log.info("Received Message: {}", user.toString());

    }


}
