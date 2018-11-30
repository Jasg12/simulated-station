package com.sjsu.cmpe.sstreet.simulatedstation.model;

import com.sjsu.cmpe.sstreet.simulatedstation.model.SpeedType;

public class WindSpeedSensorValue extends SensorValue  {

    private Integer speed;

    private SpeedType dataType;

    public WindSpeedSensorValue() {

    }

    public WindSpeedSensorValue(Integer speed, SpeedType dataType) {

        this.speed = speed;
        this.dataType = dataType;
    }

    public Integer getSpeed() {

        return speed;
    }

    public void setSpeed(Integer speed) {

        this.speed = speed;
    }

    public SpeedType getDataType() {

        return dataType;
    }

    public void setDataType(SpeedType dataType) {

        this.dataType = dataType;
    }

}
