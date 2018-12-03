package com.sjsu.cmpe.sstreet.simulatedstation.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import com.sjsu.cmpe.sstreet.simulatedstation.service.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/smart_node")
public class SmartNodeController {

    private final SmartNodeService smartNodeService;
    private SmartClusterService smartClusterService;
    private Logger log;

    @Autowired
    public SmartNodeController(
        SmartNodeService smartNodeService,
        SmartClusterService smartClusterService,
        Logger log)
    {
        this.smartNodeService = smartNodeService;
        this.smartClusterService = smartClusterService;
        this.log = log;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = "application/json")
    public SmartNode createSmartNode(@RequestBody SmartNode smartNode){

        SmartCluster cluster = smartClusterService.getSmartCluster();

        return smartNodeService.createSmartNode(smartNode, cluster);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{internalId}/registered/{registered}", produces = "application/json")
    public JsonNode updateSmartNode(
        @RequestBody SmartNode smartNode,
        @PathVariable(value = "internalId") Integer internalId,
        @PathVariable(value = "registered") Boolean registered){

        SmartCluster cluster = smartClusterService.getSmartCluster();
        smartNode.setSmartCluster(cluster);
        smartNode.setInternalId(internalId);
        smartNode.setRegistered(registered);

        return convertNodeToJson(smartNodeService.updateSmartNode(smartNode));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nodes", produces = "application/json")
    public List<JsonNode> getAllSmartNodes(){

        ObjectMapper om = new ObjectMapper();
        List<JsonNode> result = new ArrayList();
        List<SmartNode> nodes = smartNodeService.getAllSmartNodes();
        for(SmartNode node:nodes){
            JsonNode jsonNode = convertNodeToJson(node);
            result.add(jsonNode);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public JsonNode getSmartNodeById(@PathVariable(value = "id") Integer id){

        SmartNode node = smartNodeService.getSmartNodeById(id);

        return convertNodeToJson(node);
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

    @RequestMapping(method = RequestMethod.PUT, value = "/node/registered", produces = "application/json")
    public void registeredNodes(@RequestBody SmartNode smartNode){
        log.info("Getting node registering event node:{}", smartNode);
        smartNodeService.registeredNode(smartNode);
    }

    private JsonNode convertNodeToJson(SmartNode node){
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.convertValue(node, JsonNode.class);
        ((ObjectNode)jsonNode).put("internalId", node.getInternalId());
        ((ObjectNode)jsonNode).put("registered", node.getRegistered());

        return jsonNode;
    }
}
