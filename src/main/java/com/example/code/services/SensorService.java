package com.example.code.services;

import com.example.code.models.Measurement;
import com.example.code.models.Sensor;
import com.example.code.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id){
        Optional<Sensor> foundSensor = sensorRepository.findById(id);
        return foundSensor.orElse(null);
    }
}
