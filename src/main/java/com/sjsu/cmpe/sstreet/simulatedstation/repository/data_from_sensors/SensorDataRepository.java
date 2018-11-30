package com.sjsu.cmpe.sstreet.simulatedstation.repository.data_from_sensors;

import com.sjsu.cmpe.sstreet.simulatedstation.model.*;

import java.util.Random;

public class SensorDataRepository implements ISensorDataRepository {

    public SensorData getSensorData(Sensor sensor){

        SensorData sensorData = new SensorData();

        sensorData.setIdSensor(sensor.getIdSensor());
        sensorData.setIdSmartNode(sensor.getSmartNode().getIdSmartNode());
        sensorData.setIdSmartCluster(sensor.getSmartNode().getSmartCluster().getIdSmartCluster());
        sensorData.setTimestamp(System.currentTimeMillis());

        switch (sensor.getType()){

            case "TEMPERATURE":     sensorData.setType(SensorType.TEMPERATURE);
                                    sensorData.setValue(getTemperatureValue());
                                    break;
            case "WIND_SPEED":      sensorData.setType(SensorType.WIND_DIRECTION);
                                    sensorData.setValue(getWindDirectionValue());
                                    break;
            case "WIND_DIRECTION":  sensorData.setType(SensorType.WIND_SPEED);
                                    sensorData.setValue(getWindSpeedValue());
                                    break;
        }


        return sensorData;
    }

    public SensorValue getTemperatureValue(){

        SensorValue sensorValue = new TemperatureSensorValue();

        Random randomValue = new Random();

        ((TemperatureSensorValue) sensorValue).setDataType(TemperatureType.C);
        ((TemperatureSensorValue) sensorValue).setTemperature(5+randomValue.nextInt(10));

        return sensorValue;

    }

    public SensorValue getWindDirectionValue(){

        SensorValue sensorValue = new WindDirectionSensorValue();

        Random randomValue = new Random();

        ((WindDirectionSensorValue) sensorValue).setDirection(WindDirectionType.values()
                [randomValue.nextInt(WindDirectionType.values().length-1)]);

        return sensorValue;

    }

    private SensorValue getWindSpeedValue(){

        SensorValue sensorValue = new WindSpeedSensorValue();

        Random randomValue = new Random();

        ((WindSpeedSensorValue) sensorValue).setDataType(SpeedType.mih);
        ((WindSpeedSensorValue) sensorValue).setSpeed(1+randomValue.nextInt(15));

        return sensorValue;

    }

}
