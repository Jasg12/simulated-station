package com.sjsu.cmpe.sstreet.simulatedstation.service;


import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SmartNodeService {

    private SmartNodeRepository smartNodeRepository;
    private SensorService sensorService;
    private Logger log;
    private LocationRepository locationRepository;
    private MirroringServerService mirroringServerService;
    private RegisteringService registeringService;

    @Autowired
    public SmartNodeService(
        SmartNodeRepository smartNodeRepository,
        SensorService sensorService,
        Logger log,
        LocationRepository locationRepository,
        MirroringServerService mirroringServerService,
        RegisteringService registeringService)
    {
        this.smartNodeRepository = smartNodeRepository;
        this.sensorService = sensorService;
        this.log = log;
        this.locationRepository = locationRepository;
        this.mirroringServerService = mirroringServerService;
        this.registeringService = registeringService;
    }

    public SmartNode createSmartNode(SmartNode node, SmartCluster cluster) {

        Location location = node.getLocation();
        location = locationRepository.save(location);
        node.setSmartCluster(cluster);
        node.setInstallationDate(new Date());
        SmartNode savedNode = smartNodeRepository.save(node);
        createSensorsForNode(savedNode);

        return savedNode;
    }

    private void createSensorsForNode(SmartNode node){
        Location nodeLocation = node.getLocation();
        Location temperatureSensorLocation = new Location(nodeLocation);
        temperatureSensorLocation.setLongitude(temperatureSensorLocation.getLongitude() - 0.000051);
        temperatureSensorLocation.setLatitude(temperatureSensorLocation.getLatitude() + 0.000675);
        locationRepository.save(temperatureSensorLocation);
        Sensor temperatureSensor = new Sensor("sensor-1", "temp-1", "IBM", new Date(), SensorType.TEMPERATURE, temperatureSensorLocation, node);
        temperatureSensor = sensorService.createSensor(temperatureSensor);

        Location windSpeedSensorLocation = new Location(nodeLocation);
        windSpeedSensorLocation.setLongitude(windSpeedSensorLocation.getLongitude() + 0.000418);
        windSpeedSensorLocation.setLatitude(windSpeedSensorLocation.getLatitude() - 0.000011);
        locationRepository.save(windSpeedSensorLocation);
        Sensor windSpeedSensor = new Sensor("sensor-2", "wind-speed-2", "IBM", new Date(), SensorType.WIND_SPEED, windSpeedSensorLocation, node);
        windSpeedSensor = sensorService.createSensor(windSpeedSensor);

        Location windDirectionSensorLocation = new Location(nodeLocation);
        windDirectionSensorLocation.setLongitude(windDirectionSensorLocation.getLongitude() + 0.000128);
        windDirectionSensorLocation.setLatitude(windDirectionSensorLocation.getLatitude() + 0.001115);
        locationRepository.save(windDirectionSensorLocation);
        Sensor windDirectionSensor = new Sensor("sensor-3", "wind-direction-1", "IBM", new Date(), SensorType.WIND_DIRECTION, windDirectionSensorLocation, node);
        windDirectionSensor = sensorService.createSensor(windDirectionSensor);
    }

    public SmartNode updateSmartNode(SmartNode smartNode){

        Location location = smartNode.getLocation();
        location = locationRepository.save(location);
        SmartNode savedNode = smartNodeRepository.save(smartNode);
        if(savedNode.getRegistered()){
            mirroringServerService.updateNode(savedNode);
        }

        return savedNode;
    }

    public List<SmartNode> getAllSmartNodes(){

        Iterable<SmartNode> smartNodeIterable = smartNodeRepository.findAll();
        List<SmartNode> smartNodeList  = new ArrayList<>();

        smartNodeIterable.forEach(smartNode ->
            smartNodeList.add(smartNode)

        );

        return smartNodeList;
    }

    public SmartNode getSmartNodeById(Integer id){

        return smartNodeRepository.findById(id).get();
    }

    public SmartNode getSmartNodeByName(String name){

        Optional<SmartNode> smartNodeOptional = smartNodeRepository.findByName(name);
        List<SmartNode> smartNodeList = new ArrayList<>();

        if(!smartNodeOptional.isPresent()) {

            return null;
        }

        smartNodeOptional.ifPresent(smartNode ->
                smartNodeList.add(smartNode)

        );

        return smartNodeList.get(0);

    }

    public SmartNode getSmartNodeByLocation(Location location){


        return smartNodeRepository.findByLocation(location);

    }

    public List<SmartNode> getSmartNodeBySmartCluster(SmartCluster smartCluster) {

        return smartNodeRepository.findBySmartCluster(smartCluster);

    }

    public ResponseEntity<String> deleteSmartNodeById(Integer id){

        smartNodeRepository.deleteById(id);
        return ResponseEntity.ok("Smart Node Successfully Deleted");

    }

    public ResponseEntity<String> deleteSmartNodeByName(String name){

        smartNodeRepository.deleteByName(name);
        return ResponseEntity.ok("Smart Node Successfully Deleted");

    }

    public ResponseEntity<String> deleteSmartNodeBySmartCluster(SmartCluster smartCluster){

        smartNodeRepository.deleteBySmartCluster(smartCluster);
        return ResponseEntity.ok("Smart Node Successfully Deleted");

    }

    public SmartNodeData getSmartNodeData(SmartNode smartNode){

        return null;
    }

    public SmartNodeData getSmartNodeDataTest(){

        return null;
    }

    public List<SmartNode> getUnregisteredNodes(){

        return smartNodeRepository.findAllByRegistered(false);
    }

    public SmartNode registeredNode(SmartNode node){

        SmartNode currentNode = smartNodeRepository.findById(node.getInternalId()).get();
        Location location = currentNode.getLocation();
        location.setIdLocation(node.getLocation().getIdLocation());
        locationRepository.save(location);

        if(currentNode != null){
            currentNode.setIdSmartNode(node.getIdSmartNode());
            currentNode.setRegistered(true);
        }
        log.info("Node registered successfully node:{}", currentNode);
        SmartNode savedNode = smartNodeRepository.save(currentNode);

        List<Sensor> sensors = sensorService.getSensorBySmartNode(savedNode);
        for(Sensor sensor:sensors){
            registeringService.registerSensor(sensor);
        }

        return savedNode;
    }
}
