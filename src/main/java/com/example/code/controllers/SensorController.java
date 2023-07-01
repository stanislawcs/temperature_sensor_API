package com.example.code.controllers;

import com.example.code.models.Sensor;
import com.example.code.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<Sensor> getAll(){
        return sensorService.findAll();
    }

    @GetMapping("/{id}")
    public Sensor getOne(@PathVariable("id") int id){
        return sensorService.findOne(id);
    }

}
