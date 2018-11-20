package com.sjsu.cmpe.street.simulatedstation.Dao;

import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakedata")
public class FakeSensorDaoImp implements SensorDao {
    private static Map<Integer, Sensor> sensors;

    static { sensors = new HashMap<Integer, Sensor>(){
            {
                put(1, new Sensor(1,"S1","temperature", "Street 1"));
                put(2, new Sensor(2,"S2","temperature", "Street 2"));
                put(3, new Sensor(3,"S3","temperature", "Street 3"));
                put(4, new Sensor(4,"S4","temperature", "Street 4"));
            }

    };}
        @Override
        public Collection<Sensor> getAllSensors(){
            return this.sensors.values();
        }

        @Override
        public Sensor getSensorById(int id){
        return this.sensors.get(id);
        }

    @Override
    public void removeSensorById(int id) {
        this.sensors.remove(id);

    }
    @Override
    public void updateSensor(Sensor sensor){
        Sensor s = sensors.get(sensor.getId());
        s.setType(sensor.getType());
        s.setLocation(sensor.getLocation());
        sensors.put(sensor.getId(), sensor);
    }

    @Override
    public void insertSensor(Sensor sensor) {
        this.sensors.put(sensor.getId(), sensor);
    }
}

