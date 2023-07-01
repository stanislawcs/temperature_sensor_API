package com.example.code.controllers;

import com.example.code.models.Sensor;
import com.example.code.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid Sensor sensor,
                                                     BindingResult bindingResult){

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
