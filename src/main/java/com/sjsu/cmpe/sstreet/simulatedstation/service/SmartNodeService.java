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
import java.util.List;
import java.util.Optional;

@Service
public class SmartNodeService {

    private SmartNodeRepository smartNodeRepository;
    private ISensorDataRepository sensorDataRepository;
    private SensorService sensorService;
    private Logger log;
    private LocationRepository locationRepository;

    @Autowired
    public SmartNodeService(
        SmartNodeRepository smartNodeRepository,
        SensorService sensorService,
        Logger log,
        LocationRepository locationRepository)
    {
        this.smartNodeRepository = smartNodeRepository;
        this.sensorDataRepository = new SensorDataRepository();
        this.sensorService = sensorService;
        this.log = log;
        this.locationRepository = locationRepository;
    }

    public ResponseEntity<String> createSmartNode(SmartNode smartNode) {


        SmartNode savedSmartNode = smartNodeRepository.save(smartNode);

        if(null != savedSmartNode){

            return ResponseEntity.ok("Smart Node Created with ID: "+savedSmartNode.getIdSmartNode());
        }else{

            return new ResponseEntity<>("A Smart Node at requested location already exists", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> updateSmartNode(SmartNode smartNode){



        Optional<SmartNode> smartNodeResult = smartNodeRepository.findById(smartNode.getIdSmartNode());

        smartNodeResult.ifPresent(result->{
            smartNode.setName(result.getName());
            smartNode.setMake(result.getMake());
            smartNode.setModel(result.getModel());
            smartNode.setInstallationDate(result.getInstallationDate());
            smartNode.setSmartCluster(result.getSmartCluster());

        });

        if(smartNodeResult.isPresent()){

            if(null != smartNodeRepository.save(smartNode)){
                return ResponseEntity.ok("Smart Node updated");

            }else{
                return new ResponseEntity<>("Smart Node  Failed",HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }else{
            return new ResponseEntity<>("Smart Node with ID: " + smartNode.getIdSmartNode()+" does not exist",HttpStatus.BAD_REQUEST);
        }

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

        Optional<SmartNode> smartNodeOptional = smartNodeRepository.findById(id);
        List<SmartNode> smartNode = new ArrayList<>();

        if(!smartNodeOptional.isPresent()) {

            return null;
        }


        return smartNodeOptional.get();

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
