package com.sjsu.cmpe.street.simulatedstation.Dao;

import com.sjsu.cmpe.street.simulatedstation.Dao.SensorDao;
import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Qualifier("mongoData")
public class MongoStudentDaoImpl implements SensorDao {

    @Override
    public Collection<Sensor> getAllSensors() {

        return new ArrayList<Sensor>(){
            {
                add(new Sensor(10, "anj", "Temp", "street"));
            }
        };
    }

    @Override
    public Sensor getSensorById(int id) {
        return null;
    }

    @Override
    public void removeSensorById(int id) {

    }

    @Override
    public void updateSensor(Sensor sensor) {

    }

    @Override
    public void insertSensor(Sensor sensor) {

    }
}
