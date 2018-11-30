package com.sjsu.cmpe.sstreet.simulatedstation.service;

import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.data_from_sensors.ISensorDataRepository;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.data_from_sensors.SensorDataRepository;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private SensorRepository sensorRepository;

    private SmartNodeRepository smartNodeRepository;

    private ISensorDataRepository sensorDataRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository, SmartNodeRepository smartNodeRepository) {
        this.sensorRepository = sensorRepository;
        this.smartNodeRepository = smartNodeRepository;
        this.sensorDataRepository = new SensorDataRepository();
    }

    public ResponseEntity<String> createSensor(Sensor sensor) {


        Sensor savedSensor = sensorRepository.save(sensor);

        if (null != savedSensor) {

            return ResponseEntity.ok("Smart Node Created with ID: " + savedSensor.getIdSensor());
        } else {

            return new ResponseEntity<>("A Smart Node at requested location already exists", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> updateSensor(Sensor sensor) {


        Optional<Sensor> sensorResult = sensorRepository.findById(sensor.getIdSensor());

        if (sensorResult.isPresent()) {

            if (null != sensorRepository.save(sensor)) {
                return ResponseEntity.ok("Smart Node updated");

            } else {
                return new ResponseEntity<>("Smart Node  Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>("Smart Node with ID: " + sensor.getIdSensor() + " does not exist", HttpStatus.BAD_REQUEST);
        }

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

        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        List<Sensor> sensor = new ArrayList<>();

        if (!sensorOptional.isPresent()) {

            return null;
        }


        return sensor.get(0);

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

    public SensorData getSensorData(Sensor sensor){

        return sensorDataRepository.getSensorData(sensor);

    }

}


