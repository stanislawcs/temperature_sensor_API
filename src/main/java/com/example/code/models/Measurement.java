package com.example.code.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Temperature value should be not null")
    @Min(value = -100, message = "Temperature cannot be lower than -100 C")
    @Max(value = 100, message = "Temperature cannot be greater than 100 C")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "Expression should be not null")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    @JsonBackReference
    private Sensor sensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
