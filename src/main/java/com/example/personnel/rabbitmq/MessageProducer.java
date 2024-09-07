package com.example.personnel.rabbitmq;

import com.example.personnel.model.personnel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    public MessageProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendPersonnelUpdate(personnel personnel) {
        try {
            String message = objectMapper.writeValueAsString(personnel);
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            logger.info("Personel güncelleme mesajı gönderildi: {}", message);
        } catch (JsonProcessingException e) {
            logger.error("Mesaj dönüştürme hatası: {}", e.getMessage());
        }
    }

    public void sendPersonnelDeletion(personnel personnel) {
        try {
            String message = objectMapper.writeValueAsString(personnel);
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            logger.info("Personel silme mesajı gönderildi: {}", message);
        } catch (JsonProcessingException e) {
            logger.error("Mesaj dönüştürme hatası: {}", e.getMessage());
        }
    }
}
