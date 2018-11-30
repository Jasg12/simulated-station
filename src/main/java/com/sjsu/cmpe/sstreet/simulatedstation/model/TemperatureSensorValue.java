package com.sjsu.cmpe.sstreet.simulatedstation.model;

import com.sjsu.cmpe.sstreet.simulatedstation.model.TemperatureType;

public class TemperatureSensorValue extends SensorValue {

    private Integer temperature;

    private TemperatureType dataType;

    public TemperatureSensorValue(Integer temperature, TemperatureType dataType) {

        this.temperature = temperature;
        this.dataType = dataType;
    }

    public TemperatureSensorValue() {

    }

    public Integer getTemperature() {

        return temperature;
    }

    public void setTemperature(Integer temperature) {

        this.temperature = temperature;
    }

    public TemperatureType getDataType() {

        return dataType;
    }

    public void setDataType(TemperatureType dataType) {

        this.dataType = dataType;
    }
}
