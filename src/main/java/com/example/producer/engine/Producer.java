package com.example.producer.engine;

import com.example.producer.machine.Machine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.crypto.Mac;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.logging.Logger;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class Producer {
    static final Logger LOGGER =
            Logger.getLogger(Producer.class.getName());
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    public Producer(KafkaTemplate<String, String> kafkaTemplate,ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String topic, String key, List<Machine> machine) throws JsonProcessingException {
        LOGGER.info("send topic info " +
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        String machineJson = objectMapper.writeValueAsString(machine);

        // "INPUT_DATA", "IN_KEY", json
        kafkaTemplate.send(topic, key, machineJson);


    }
}