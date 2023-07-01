package com.example.code.services;

import com.example.code.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

}
