package com.example.producer.sensor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
public class Sensor {
    private SensorType sensorType;
    private double value;
    private Timestamp dateValue;
}
