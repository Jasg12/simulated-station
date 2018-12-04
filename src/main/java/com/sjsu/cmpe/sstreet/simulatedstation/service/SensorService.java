package com.sjsu.cmpe.sstreet.simulatedstation.service;

import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SensorService {

    private SensorRepository sensorRepository;
    private SmartNodeRepository smartNodeRepository;
    private LocationRepository locationRepository;


    @Autowired
    public SensorService(
        SensorRepository sensorRepository,
        SmartNodeRepository smartNodeRepository,
        LocationRepository locationRepository)
    {
        this.sensorRepository = sensorRepository;
        this.smartNodeRepository = smartNodeRepository;
        this.locationRepository = locationRepository;
    }

    public Sensor createSensor(Sensor sensor) {

        return sensorRepository.save(sensor);
    }

    public Sensor update(Sensor sensor) {

        Location location = sensor.getLocation();
        location = locationRepository.save(location);

        return sensorRepository.save(sensor);
    }

    public List<Sensor> getAllSensors() {

        Iterable<Sensor> sensorIterable = sensorRepository.findAll();
        List<Sensor> sensorList = new ArrayList<>();

        sensorIterable.forEach(sensor ->
                sensorList.add(sensor)
        );

        return sensorList;
    }

    public Sensor getSensorById(Integer id) {

        return sensorRepository.findById(id).get();

    }

    public List<Sensor> getAllByNodeId(Integer nodeId){

        return sensorRepository.findAllBySmartNode_IdSmartNode(nodeId);
    }

    public List<Sensor> getSensorBySmartNode(SmartNode smartNode) {

        return sensorRepository.findBySmartNode(smartNode);

    }

    public List<Sensor> getSensorBySmartCluster(SmartCluster smartCluster) {

        List<Sensor> sensorList = new ArrayList<>();

        List<SmartNode> smartNodeList = smartNodeRepository.findBySmartCluster(smartCluster);

        for (SmartNode smartNode:smartNodeList)
            sensorList.addAll(sensorRepository.findBySmartNode(smartNode));

       return sensorList;

    }

    public ResponseEntity<String> deleteSensorById(Integer id) {

        sensorRepository.deleteById(id);
        return ResponseEntity.ok("Sensor Successfully Deleted");

    }

    public ResponseEntity<String> deleteSensorBySmartNode(SmartNode smartNode) {

        sensorRepository.deleteBySmartNode(smartNode);

        return ResponseEntity.ok("Sensor Successfully Deleted");

    }

    public ResponseEntity<String> deleteSensorBySmartCluster(SmartCluster smartCluster) {

        List<SmartNode> smartNodeList = smartNodeRepository.findBySmartCluster(smartCluster);

        for (SmartNode smartNode:smartNodeList)
            sensorRepository.deleteBySmartNode(smartNode);

        return ResponseEntity.ok("Sensor Successfully Deleted");

    }

    public SensorData getSensorData(Sensor sensor, SmartCluster cluster){

        switch (sensor.getType()){
            case TEMPERATURE:
                return generateTemperatureSensorData(sensor, cluster);
            case WIND_SPEED:
                return generateWindSpeedSensorData(sensor, cluster);
            case WIND_DIRECTION:
                return generateWindDirectionSensorData(sensor, cluster);
        }

        return null;
    }

    private SensorData generateCoreOfSensorData(Sensor sensor, SmartCluster cluster){
        SensorData sensorData = new SensorData();
        sensorData.setIdSmartCluster(cluster.getIdSmartCluster());
        sensorData.setIdSmartNode(sensor.getSmartNode().getIdSmartNode());
        sensorData.setIdSensor(sensor.getIdSensor());
        sensorData.setTimestamp(new Date().getTime());

        return sensorData;
    }

    private SensorData generateTemperatureSensorData(Sensor sensor, SmartCluster cluster){
        SensorData sensorData = generateCoreOfSensorData(sensor, cluster);
        sensorData.setType(SensorType.TEMPERATURE);
        TemperatureSensorValue value = new TemperatureSensorValue();
        value.setDataType(TemperatureType.C);
        Random ran = new Random();
        int temperature = ran.nextInt(35) + 1;
        value.setTemperature(temperature);
        sensorData.setValue(value);

        return sensorData;
    }

    private SensorData generateWindSpeedSensorData(Sensor sensor, SmartCluster cluster){
        SensorData sensorData = generateCoreOfSensorData(sensor, cluster);
        sensorData.setType(SensorType.WIND_SPEED);
        WindSpeedSensorValue value = new WindSpeedSensorValue();
        value.setDataType(SpeedType.kmh);
        Random ran = new Random();
        int speed = ran.nextInt(100) + 1;
        value.setSpeed(speed);
        sensorData.setValue(value);

        return sensorData;
    }

    private SensorData generateWindDirectionSensorData(Sensor sensor, SmartCluster cluster){
        SensorData sensorData = generateCoreOfSensorData(sensor, cluster);
        sensorData.setType(SensorType.WIND_DIRECTION);
        WindDirectionSensorValue value = new WindDirectionSensorValue();
        value.setDirection(WindDirectionType.getRandomDirection());
        sensorData.setValue(value);

        return sensorData;
    }
}


