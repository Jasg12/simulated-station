package com.sjsu.cmpe.sstreet.simulatedstation;

import com.sjsu.cmpe.sstreet.simulatedstation.model.Location;
import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartCluster;
import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartNode;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql.SmartNodeRepository;
import com.sjsu.cmpe.sstreet.simulatedstation.service.RegisteringService;
import com.sjsu.cmpe.sstreet.simulatedstation.service.SmartClusterService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationStatrUp implements ApplicationListener<ApplicationReadyEvent> {

    private Logger log;

    @Value(value = "${new.cluster.auto.register:false}")
    private Boolean autoRegister;

    private RegisteringService registeringService;
    private SmartClusterService smartClusterService;
    private SmartNodeRepository smartNodeRepository;

    @Autowired
    public ApplicationStatrUp(
        Logger log,
        RegisteringService registeringService,
        SmartClusterService smartClusterService,
        SmartNodeRepository smartNodeRepository) {

        this.log = log;
        this.registeringService = registeringService;
        this.smartClusterService = smartClusterService;
        this.smartNodeRepository = smartNodeRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if(autoRegister){
            log.info("Auto register task starting ...");
            autoRegisterClusterTask();
        } else {
            log.info("Auto registering task disabled.");
        }

    //    createUnregisteredNode();
    }

    private void autoRegisterClusterTask(){
        try{
            registeringService.registerCluster();
            log.info("Auto registration task done.");
        } catch (Exception exception){
            log.error("Error during auto registration cluster event", exception);
        }
    }

    private void createUnregisteredNode(){
        Location location = new Location(37.335720, -121.901672, "CA", "San Jose", "W Julian St", 95110);
        SmartCluster cluster = smartClusterService.getSmartCluster();
        SmartNode node = new SmartNode();
        node.setName("node-1");
        node.setModel("weather-node");
        node.setMake("IBM");
        node.setInstallationDate(new Date());
        node.setLocation(location);
        node.setSmartCluster(cluster);

        smartNodeRepository.save(node);
    }
}
