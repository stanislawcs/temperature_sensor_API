package com.example.code.services;

import com.example.code.models.Measurement;
import com.example.code.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurement> findAll(){
       return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){
        measurementsRepository.save(measurement);
    }

    public long calculateCountOfRainyDays(){
        return measurementsRepository.findAll().stream().filter(Measurement::isRaining).count();
    }
}
