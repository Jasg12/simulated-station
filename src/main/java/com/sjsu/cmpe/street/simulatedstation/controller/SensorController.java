package com.sjsu.cmpe.street.simulatedstation.controller;

import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import com.sjsu.cmpe.street.simulatedstation.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;
import java.util.Random;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService = new SensorService();

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sensor getSensorsById(@PathVariable("id") int id) {
        return sensorService.getSensorById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSensorsById(@PathVariable("id") int id) {
        sensorService.removeSensorById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateSensor(@RequestBody Sensor sensor) {
        sensorService.updateSensor(sensor);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertSensor(@RequestBody Sensor sensor) {
        sensorService.insertSensor(sensor);
    }

}
