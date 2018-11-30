package com.sjsu.cmpe.sstreet.simulatedstation.model;

import com.sjsu.cmpe.sstreet.simulatedstation.model.WindDirectionType;

public class WindDirectionSensorValue extends SensorValue  {

    private WindDirectionType direction;

    public WindDirectionSensorValue() {

    }

    public WindDirectionSensorValue(WindDirectionType direction) {

        this.direction = direction;
    }

    public WindDirectionType getDirection() {

        return direction;
    }

    public void setDirection(WindDirectionType direction) {

        this.direction = direction;
    }

}
