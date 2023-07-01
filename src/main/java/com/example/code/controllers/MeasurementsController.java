package com.example.code.controllers;

import com.example.code.models.Measurement;
import com.example.code.services.MeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @GetMapping()
    public List<Measurement> getAll(){
        return measurementsService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid Measurement measurement,
                                                     BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RuntimeException();
        }

        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public long getCountOfRainyDays(){
        return measurementsService.calculateCountOfRainyDays();
    }


}
