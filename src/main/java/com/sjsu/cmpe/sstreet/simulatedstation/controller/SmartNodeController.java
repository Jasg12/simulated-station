package com.sjsu.cmpe.sstreet.simulatedstation.controller;

import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import com.sjsu.cmpe.sstreet.simulatedstation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/smart_node")
public class SmartNodeController {

    private final SmartNodeService smartNodeService;

    @Autowired
    public SmartNodeController(SmartNodeService smartNodeService) {
        this.smartNodeService = smartNodeService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public @ResponseBody ResponseEntity<String> createSmartNode(@RequestBody SmartNode smartNode){

        return smartNodeService.createSmartNode(smartNode);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public @ResponseBody ResponseEntity<String> updateSmartNode(@RequestBody SmartNode smartNode){

        return smartNodeService.updateSmartNode(smartNode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public @ResponseBody List<SmartNode> getAllSmartNode(){

        return smartNodeService.getAllSmartNodes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byId/{idSmartNode}")
    public @ResponseBody SmartNode getSmartNodeById(@PathVariable(value = "idSmartNode") Integer idSmartNode){

        return smartNodeService.getSmartNodeById(idSmartNode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byLocation")
    public @ResponseBody SmartNode getSmartNodeByLocation(@RequestBody Location location){

        return smartNodeService.getSmartNodeByLocation(location);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byName/{name}")
    public @ResponseBody SmartNode getSmartNodeByName(@PathVariable(value = "name") String name){

        return smartNodeService.getSmartNodeByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/bySmartCluster")
    public @ResponseBody List<SmartNode> getSmartNodeBySmartCluster(@RequestBody SmartCluster smartCluster){

        return smartNodeService.getSmartNodeBySmartCluster(smartCluster);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byName/{name}")
    public @ResponseBody ResponseEntity<String> deleteSmartNodeByName(@PathVariable(value = "name") String name){

        return smartNodeService.deleteSmartNodeByName(name);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteSmartNodeById(@PathVariable(value = "id") Integer idSmartNode){

        return smartNodeService.deleteSmartNodeById(idSmartNode);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/bySmartCluster")
    public @ResponseBody ResponseEntity<String> deleteSmartNodeBySmartCluster(@RequestBody SmartCluster smartCluster){

        return smartNodeService.deleteSmartNodeBySmartCluster(smartCluster);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/get/smartNodeData")
    public @ResponseBody SmartNodeData getSmartNodeData(@RequestBody SmartNode smartNode){

        return smartNodeService.getSmartNodeData(smartNode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/smartNodeDataTest")
    public @ResponseBody SmartNodeData getSmartNodeDataTest(){

        return smartNodeService.getSmartNodeDataTest();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nodes/unregistered", produces = "application/json")
    public List<SmartNode> getUnregisteredNodes(){

        return smartNodeService.getUnregisteredNodes();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/node/registered", produces = "application/json")
    public SmartNode registeredNodes(@RequestBody SmartNode smartNode){

        return smartNodeService.registeredNode(smartNode);
    }

}
