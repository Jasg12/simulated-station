package com.sjsu.cmpe.sstreet.simulatedstation;

import com.sjsu.cmpe.sstreet.simulatedstation.service.RegisteringService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStatrUp implements ApplicationListener<ApplicationReadyEvent> {

    private Logger log;

    @Value(value = "${new.cluster.auto.register:false}")
    private Boolean autoRegister;

    private RegisteringService registeringService;

    @Autowired
    public ApplicationStatrUp(Logger log, RegisteringService registeringService) {

        this.log = log;
        this.registeringService = registeringService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if(autoRegister){
            log.info("Auto register task starting ...");
            autoRegisterClusterTask();
        } else {
            log.info("Auto registering task disabled.");
        }
    }

    private void autoRegisterClusterTask(){
        try{
            registeringService.registerCluster();
            log.info("Auto registration task done.");
        } catch (Exception exception){
            log.error("Error during auto registration cluster event", exception);
        }

    }
}
