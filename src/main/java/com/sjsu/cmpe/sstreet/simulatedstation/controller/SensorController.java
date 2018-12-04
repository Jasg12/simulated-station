package com.sjsu.cmpe.sstreet.simulatedstation.controller;

import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import com.sjsu.cmpe.sstreet.simulatedstation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/sensor")
public class SensorController {

    private final SensorService sensorService;
    private SmartClusterService smartClusterService;


    @Autowired
    public SensorController(
        SensorService sensorService,
        SmartClusterService smartClusterService)
    {
        this.sensorService = sensorService;
        this.smartClusterService = smartClusterService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Sensor createSensor(@RequestBody Sensor sensor){

        return sensorService.createSensor(sensor);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public Sensor updateSensor(@RequestBody Sensor sensor){

        return sensorService.update(sensor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public @ResponseBody
    List<Sensor> getAllSensor(){

        return sensorService.getAllSensors();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sensorId}", produces = "application/json")
    public Sensor getSensorById(@PathVariable(value = "sensorId") Integer sensorId){

        return sensorService.getSensorById(sensorId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sensors/nodeId/{nodeId}", produces = "application/json")
    public List<Sensor> getSensorBySmartNode(@PathVariable(value = "nodeId") Integer nodeId){

        return sensorService.getAllByNodeId(nodeId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/bySmartCluster")
    public @ResponseBody List<Sensor> getSensorBySmartCluster(@RequestBody SmartCluster smartCluster){

        return sensorService.getSensorBySmartCluster(smartCluster);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteSensorById(@PathVariable(value = "id") Integer idSensor){

        return sensorService.deleteSensorById(idSensor);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/bySmartNode")
    public @ResponseBody ResponseEntity<String> deleteSensorBySmartNode(@RequestBody SmartNode smartNode){

        return sensorService.deleteSensorBySmartNode(smartNode);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/bySmartCluster")
    public @ResponseBody ResponseEntity<String> deleteSensorBySmartCluster(@RequestBody SmartCluster smartCluster){

        return sensorService.deleteSensorBySmartCluster(smartCluster);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/get/sensorData")
    public @ResponseBody SensorData getSensorData(@RequestBody Sensor sensor){

        SmartCluster cluster = smartClusterService.getSmartCluster();

        return sensorService.getSensorData(sensor, cluster);
    }


}

