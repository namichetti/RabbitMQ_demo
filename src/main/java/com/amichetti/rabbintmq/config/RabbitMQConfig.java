package com.amichetti.rabbintmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.queue}")
    private String queue;

    @Value("${spring.json.queue}")
    private String jsonQueue;

    @Value("${spring.exchange}")
    private String exchange;

    //ROUTING KEY: usa el exchange para decidir a qué queue enviar un mensaje.
    //Dependiendo del tipo de exchange,
    // el mensaje se envía a una o varias colas según esta clave.
    @Value("${spring.routing.key}")
    private String routingKey;

    @Value("${spring.routing.json.key}")
    private String routingJsonKey;

    @Value("${spring.json.exchange}")
    private String jsonExchange;

    //QUEUE: estructura de almacenamiento de mensajes en orden FIFO,
    // el consumer lee desde ahí.
    @Bean
    public Queue rabbitMQQueue() {
        return new  Queue(queue);
    }

    @Bean
    public Queue rabbitMQJsonQueue() {
        return new Queue(jsonQueue);
    }


        //EXCHANGE:Cuando un productor envía un mensaje, no lo manda directamente a la cola,
    //sino al exchange, que decide a qué queue(s) debe ir el mensaje
    @Bean
    public TopicExchange rabbitMQExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public TopicExchange rabbitMQJsonExchange() {
        return new TopicExchange(jsonExchange);
    }

    //BINDING: Dice qué mensajes enviados al exchange deben ir a qué cola.
    @Bean
    public Binding rabbitMQBinding() {
        return BindingBuilder
                .bind(rabbitMQQueue())
                .to(rabbitMQExchange())
                .with(routingKey);
    }

    @Bean
    public Binding rabbitMQJsonBinding() {
        return BindingBuilder
                .bind(rabbitMQJsonQueue())
                .to(rabbitMQJsonExchange())
                .with(routingJsonKey);
    }

    //Porque ahora vamos a enviar JSON
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
