package com.example.code.controllers;

import com.example.code.models.Sensor;
import com.example.code.services.SensorService;
import com.example.code.util.SensorErrorResponse;
import com.example.code.util.SensorNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                message.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";\n");
            }
            throw new SensorNotCreatedException(message.toString());
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<>(sensorErrorResponse,HttpStatus.BAD_REQUEST);
    }

}
