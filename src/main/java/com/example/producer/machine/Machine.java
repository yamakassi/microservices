package com.example.producer.machine;


import com.example.producer.sensor.Sensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Machine {

    private Long id;
    private String machineName;

    private StatusMachine machineStatus;
    private Date machineServiceDate;
    private List<Sensor> sensors;

}
