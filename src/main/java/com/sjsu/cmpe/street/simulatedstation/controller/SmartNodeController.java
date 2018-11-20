package com.sjsu.cmpe.street.simulatedstation.controller;

import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import com.sjsu.cmpe.street.simulatedstation.Entity.Smart;
import com.sjsu.cmpe.street.simulatedstation.service.SensorService;
import com.sjsu.cmpe.street.simulatedstation.service.SmartNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/smarts")
public class SmartNodeController {

    @Autowired
    private SmartNodeService smartNodeService = new SmartNodeService();

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Smart> getAllSmartNodes() { return smartNodeService.getAllSmartNodes(); }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Smart getSmartNodesById(@PathVariable("id") int id) {
            return smartNodeService.getSmartNodesById(id); }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        public void deleteSmartNodesById(@PathVariable("id") int id) { smartNodeService.removeSmartNodesById(id);}

        @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
        public void updateSmartNodesById(@RequestBody Smart smart) {
            smartNodeService.updateSmartNodesById(smart);
        }

        @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
        public void insertSmartNodesById(@RequestBody Smart smart) {
            smartNodeService.insertSmartNodesById(smart);
        }

    }


