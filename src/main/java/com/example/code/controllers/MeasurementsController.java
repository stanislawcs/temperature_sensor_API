package com.example.code.controllers;

import com.example.code.dto.MeasurementDTO;
import com.example.code.dto.MeasurementsResponse;
import com.example.code.models.Measurement;
import com.example.code.services.MeasurementsService;
import com.example.code.util.MeasurementErrorResponse;
import com.example.code.util.MeasurementNotCreatedException;
import com.example.code.util.MeasurementsValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final ModelMapper modelMapper;
    private final MeasurementsService measurementsService;
    private final MeasurementsValidator measurementsValidator;


    @Autowired
    public MeasurementsController(ModelMapper modelMapper,
                                  MeasurementsService measurementsService,
                                  MeasurementsValidator measurementsValidator) {
        this.modelMapper = modelMapper;
        this.measurementsService = measurementsService;
        this.measurementsValidator = measurementsValidator;
    }

    @GetMapping()
    public MeasurementsResponse getAll() {
        return new MeasurementsResponse(measurementsService.findAll().stream()
                .map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementsValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                message.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }

            throw new MeasurementNotCreatedException(message.toString());
        }

        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public long getCountOfRainyDays() {
        return measurementsService.calculateCountOfRainyDays();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {

        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

}
