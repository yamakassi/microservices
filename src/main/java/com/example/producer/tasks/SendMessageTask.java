package com.example.producer.tasks;

import com.example.producer.engine.Producer;
import com.example.producer.machine.Machine;
import com.example.producer.machine.StatusMachine;
import com.example.producer.sensor.Sensor;
import com.example.producer.sensor.SensorType;
import com.example.producer.util.GeneratorData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class SendMessageTask {
    private final Logger logger = LoggerFactory.getLogger(SendMessageTask.class);
    private List<Machine> pastDataMachine = new ArrayList<>();
    @Value("${config.topic.name}")
    private  static String topic_name = "machine";

    private final Producer producer;
    private final GeneratorData generatorData;

    public SendMessageTask(Producer producer, GeneratorData generatorData) {

        this.producer = producer;
        this.generatorData = generatorData;
        this.pastDataMachine= generatorData.generateInitMachineData();
    }

    // run every 0.5 sec
    @Scheduled(fixedRate = 500)
    @Async
    public void send() throws JsonProcessingException {

        this.producer.sendMessage(topic_name, "machine_data",pastDataMachine);
        pastDataMachine = generatorData.generateMachineTestData(pastDataMachine);


    }
}