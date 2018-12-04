package com.sjsu.cmpe.sstreet.simulatedstation.service;

import com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql.*;
import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.*;

@Service
public class SmartClusterService {

    @Value(value = "${cluster.make:unknown}")
    private String clusterMake;
    @Value(value = "${cluster.model:unknown}")
    private String clusterModel;
    @Value(value = "${cluster.name:unknown}")
    private String clusterName;
    @Value(value = "${mirroring.server.url:unknown}")
    private String url;
    @Value(value = "${cluster.location.longitude:unknown}")
    private double clusterLongitude;
    @Value(value = "${cluster.location.latitude:unknown}")
    private double clusterLatitude;
    @Value(value = "${cluster.location.state:unknown}")
    private String clusterState;
    @Value(value = "${cluster.location.city:unknown}")
    private String clusterCity;
    @Value(value = "${cluster.location.street:unknown}")
    private String clusterStreet;
    @Value(value = "${cluster.location.zipCode:unknown}")
    private Integer clusterZipCode;
    @Value(value = "${cluster.serial.number:unknown}")
    private String serialNumber;

    private SmartClusterRepository smartClusterRepository;
    private LocationRepository locationRepository;
    private MirroringServerService mirroringServerService;

    private Logger log;


    @Autowired
    public SmartClusterService(
        SmartClusterRepository smartClusterRepository,
        LocationRepository locationRepository,
        MirroringServerService mirroringServerService,
        Logger log) {

        this.smartClusterRepository = smartClusterRepository;
        this.locationRepository = locationRepository;
        this.mirroringServerService = mirroringServerService;
        this.log = log;
    }

    public ResponseEntity<String> createSmartCluster(SmartCluster smartCluster) {

        SmartCluster savedSmartCluster = smartClusterRepository.save(smartCluster);

        if(null != savedSmartCluster){

            return ResponseEntity.ok("Smart Cluster Created with ID: "+savedSmartCluster.getIdSmartCluster());
        }else{

            return new ResponseEntity<>("A Smart Cluster at requested location already exists", HttpStatus.BAD_REQUEST);
        }

    }

    public SmartCluster update(SmartCluster smartCluster){
        SmartCluster currentCluster = getSmartCluster();
        currentCluster.setName(smartCluster.getName());
        currentCluster.setModel(smartCluster.getModel());
        currentCluster.setMake(smartCluster.getMake());
        currentCluster.setUrl(smartCluster.getUrl());
        currentCluster.setIdSmartCluster(smartCluster.getIdSmartCluster());
        currentCluster.setRegistered(smartCluster.getRegistered());

        Location currentLocation = currentCluster.getLocation();
        currentLocation.setIdLocation(smartCluster.getLocation().getIdLocation());
        currentLocation.setState(smartCluster.getLocation().getState());
        currentLocation.setCity(smartCluster.getLocation().getCity());
        currentLocation.setStreet(smartCluster.getLocation().getStreet());
        currentLocation.setLatitude(smartCluster.getLocation().getLatitude());
        currentLocation.setLongitude(smartCluster.getLocation().getLongitude());
        currentLocation.setZipCode(smartCluster.getLocation().getZipCode());
        locationRepository.save(currentLocation);
        SmartCluster savedCluster = smartClusterRepository.save(currentCluster);
        log.info("Locally update cluster:{}", savedCluster);
        mirroringServerService.updateCluster(savedCluster);

        return savedCluster;
    }

    public List<SmartCluster> getAllSmartClusters(){

        Iterable<SmartCluster> smartClusterIterable = smartClusterRepository.findAll();
        List<SmartCluster> smartClusterList  = new ArrayList<>();

        smartClusterIterable.forEach(smartCluster ->
            smartClusterList.add(smartCluster)

        );

        return smartClusterList;
    }

    public SmartCluster getSmartClusterById(Integer id){

        Optional<SmartCluster> smartClusterOptional = smartClusterRepository.findById(id);
        List<SmartCluster> smartCluster = new ArrayList<>();

        if(!smartClusterOptional.isPresent()) {

            return null;
        }

        smartClusterOptional.ifPresent( smartCluster1 ->

        smartCluster.add(smartCluster1)

        );
        return smartCluster.get(0);

    }

    public SmartCluster getSmartClusterByName(String Name){

        Optional<SmartCluster> smartClusterOptional = smartClusterRepository.findByName(Name);
        List<SmartCluster> smartCluster = new ArrayList<>();

        if(!smartClusterOptional.isPresent()) {

            return null;
        }

        smartClusterOptional.ifPresent( smartCluster1 ->

                smartCluster.add(smartCluster1)

        );

        return smartCluster.get(0);

    }

    public SmartCluster getSmartClusterByLocation(Location location){

        SmartCluster smartCluster = smartClusterRepository.findByLocation(location);

        return smartCluster;


    }

    public ResponseEntity<String> deleteSmartClusterById(Integer id){

        smartClusterRepository.deleteById(id);
        return ResponseEntity.ok("Smart Cluster Successfully Deleted");

    }

    public ResponseEntity<String> deleteSmartClusterByName(String name){

        smartClusterRepository.deleteByName(name);
        return ResponseEntity.ok("Smart Cluster Successfully Deleted");

    }

    public SmartClusterData getSmartClusterData(SmartCluster smartCluster){

        return null;

    }

    public SmartCluster getSmartCluster(){

        Iterable<SmartCluster> clusters = smartClusterRepository.findAll();
        Iterator<SmartCluster> clustersIterator = clusters.iterator();
        if(clustersIterator.hasNext()){
            return clustersIterator.next();
        }

        return null;
    }

    public SmartCluster initiateCluster(){
        SmartCluster smartCluster = new SmartCluster();
        smartCluster.setSerialNumber(serialNumber);
        smartCluster.setInstallationDate(new Date());
        smartCluster.setMake(clusterMake);
        smartCluster.setModel(clusterModel);
        smartCluster.setName(clusterName);
        smartCluster.setUrl(url);
        Location location = new Location(clusterLongitude, clusterLatitude, clusterState, clusterCity, clusterStreet, clusterZipCode);
        smartCluster.setLocation(location);
        smartCluster = smartClusterRepository.save(smartCluster);

        return smartCluster;
    }

}
