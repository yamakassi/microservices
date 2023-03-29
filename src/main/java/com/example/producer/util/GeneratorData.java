package com.example.producer.util;

import com.example.producer.machine.Machine;
import com.example.producer.machine.StatusMachine;
import com.example.producer.sensor.Sensor;
import com.example.producer.sensor.SensorType;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class GeneratorData {

    // private boolean flagInitData = false;
    static long DEFAULT_WATER_TEMP = 20;
    static long DEFAUILT_MACHINE_TEMP = 50;
    static long DEFAUILT_WATER_PRESSURE = 8;
    static long DEFAUILT_PRESSURE = 100;


    public List<Machine> generateInitMachineData() {
        Timestamp time = new Timestamp(new Date().getTime());
        Sensor sensorPressure = new Sensor(SensorType.pressure, DEFAUILT_PRESSURE,time);
        Sensor sensorMachineTemp = new Sensor(SensorType.machineTemp, DEFAUILT_MACHINE_TEMP,time);
        Sensor sensorWaterTemp = new Sensor(SensorType.waterTemp, DEFAULT_WATER_TEMP, time);
        Sensor sensorWaterPressure = new Sensor(SensorType.pressureWater, DEFAUILT_WATER_PRESSURE, time);

        List<Sensor> sensorsInit = Arrays.asList(sensorPressure, sensorMachineTemp, sensorWaterTemp, sensorWaterPressure);
        Machine m1 = new Machine(1L, "Simens bur e1", StatusMachine.work, new Date(), sensorsInit);
        Machine m2 = new Machine(2L, "Simens bur e1", StatusMachine.work, new Date(), sensorsInit);
        Machine m3 = new Machine(3L, "Hundai e1", StatusMachine.work, new Date(), sensorsInit);

        return Arrays.asList(m1, m2, m3);
    }

    public List<Machine> generateMachineTestData(List<Machine> machinesPastData) {
        /*if(flagInitData==false){
            return generateInitMachineData();
        }*/
        List<Machine> newMachineData = new ArrayList<>();

        for (Machine m : machinesPastData) {
          List<Sensor> newValueSensor =   changeValueSensor(m.getSensors());

            Machine newMachine = new Machine(m.getId(), m.getMachineName(),
                    m.getMachineStatus(), m.getMachineServiceDate(), newValueSensor);

            newMachineData.add(newMachine);


        }
        return newMachineData;


    }

    private List<Sensor> changeValueSensor(List<Sensor> sensors) {
        double directionValue = Math.random() * 2 - 1;
        Timestamp date = new Timestamp(new Date().getTime());
      return  sensors.stream().map(s -> new Sensor(s.getSensorType(), s.getValue() +
                s.getValue() * 0.2 * directionValue, date))
                .toList();

    }
}
