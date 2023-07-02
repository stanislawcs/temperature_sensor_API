package com.example.code.dto;

import com.example.code.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Temperature value should be not null")
    @Min(value = -100, message = "Temperature cannot be lower than -100 C")
    @Max(value = 100, message = "Temperature cannot be greater than 100 C")
    private double value;

    @NotNull(message = "Expression value should be not null")
    private boolean raining;

    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
