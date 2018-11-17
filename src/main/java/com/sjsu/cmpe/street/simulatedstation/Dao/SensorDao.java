package com.sjsu.cmpe.street.simulatedstation.Dao;

import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;

import java.util.Collection;

public interface SensorDao  {
    Collection<Sensor> getAllSensors();

    Sensor getSensorById(int id);

    void removeSensorById(int id);

    void updateSensor(Sensor sensor);

    void insertSensor(Sensor sensor);
}
