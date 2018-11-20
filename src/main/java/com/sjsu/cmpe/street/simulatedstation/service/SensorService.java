package com.sjsu.cmpe.street.simulatedstation.service;

import com.sjsu.cmpe.street.simulatedstation.Dao.FakeSensorDaoImp;
import com.sjsu.cmpe.street.simulatedstation.Dao.SensorDao;
import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SensorService {

    @Autowired
    @Qualifier("mysql")
    private SensorDao sensorDao;


    public Collection<Sensor> getAllSensors(){
        return this.sensorDao.getAllSensors();
    }

    public Sensor getSensorById(int id){
        return this.sensorDao.getSensorById(id);

}

    public void removeSensorById(int id) {
       this.sensorDao.removeSensorById(id);
        }

    public void updateSensor(Sensor sensor){
        this.sensorDao.updateSensor(sensor);
}

    public void insertSensor(Sensor sensor) {
        this.sensorDao.insertSensor(sensor);
    }
}

