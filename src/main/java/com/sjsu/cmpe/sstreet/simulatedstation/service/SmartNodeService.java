package com.sjsu.cmpe.sstreet.simulatedstation.service;


import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.data_from_sensors.ISensorDataRepository;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.data_from_sensors.SensorDataRepository;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.sah.LocaleNames_sah;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SmartNodeService {

    private SmartNodeRepository smartNodeRepository;
    private ISensorDataRepository sensorDataRepository;
    private SensorService sensorService;
    private Logger log;
    private LocationRepository locationRepository;
    private MirroringServerService mirroringServerService;

    @Autowired
    public SmartNodeService(
        SmartNodeRepository smartNodeRepository,
        SensorService sensorService,
        Logger log,
        LocationRepository locationRepository,
        MirroringServerService mirroringServerService)
    {
        this.smartNodeRepository = smartNodeRepository;
        this.sensorDataRepository = new SensorDataRepository();
        this.sensorService = sensorService;
        this.log = log;
        this.locationRepository = locationRepository;
        this.mirroringServerService = mirroringServerService;
    }

    public SmartNode createSmartNode(SmartNode node, SmartCluster cluster) {

        Location location = node.getLocation();
        location = locationRepository.save(location);
        node.setSmartCluster(cluster);
        node.setInstallationDate(new Date());

        return smartNodeRepository.save(node);
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

        List<Sensor> sensorList =  sensorService.getSensorBySmartNode(smartNode);
        List<SensorData> sensorDataList = new ArrayList<>();

        sensorList.stream().forEach(sensor -> sensorDataList.add(sensorDataRepository.getSensorData(sensor)));

        return (new SmartNodeData(smartNode.getIdSmartNode(), sensorDataList));

    }

    public SmartNodeData getSmartNodeDataTest(){

        List<Sensor> sensorList =  sensorService.getSensorBySmartNode(getSmartNodeById(9));
        List<SensorData> sensorDataList = new ArrayList<>();

        sensorList.stream().forEach(sensor -> sensorDataList.add(sensorDataRepository.getSensorData(sensor)));

        return (new SmartNodeData(9, sensorDataList));
    }

    public List<SmartNode> getUnregisteredNodes(){

        return smartNodeRepository.findAllByRegistered(false);
    }

    public SmartNode registeredNode(SmartNode node){

        SmartNode savedNode = smartNodeRepository.findByName(node.getName()).get();
        Location location = savedNode.getLocation();
        location.setIdLocation(node.getLocation().getIdLocation());
        locationRepository.save(location);

        if(savedNode != null){
            savedNode.setIdSmartNode(node.getIdSmartNode());
            savedNode.setRegistered(true);
        }
        log.info("Node registered successfully node:{}", savedNode);

        return smartNodeRepository.save(savedNode);
    }
}
