package com.sjsu.cmpe.sstreet.simulatedstation.service;

import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartCluster;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegisteringService {

    @Value(value = "${mirroring.server.url}")
    private String mirroringServerUrl;

    private final String REGISTER_CLUSTER_API = "/smart_cluster/create";

    private SmartClusterService smartClusterService;
    private RestTemplate restTemplate;
    private Logger log;

    @Autowired
    public RegisteringService(SmartClusterService smartClusterService, RestTemplate restTemplate, Logger log) {

        this.smartClusterService = smartClusterService;
        this.restTemplate = restTemplate;
        this.log = log;
    }

    public SmartCluster registerCluster(){

        SmartCluster smartCluster = smartClusterService.getSmartCluster();
        if(smartCluster == null){
            smartCluster = smartClusterService.initiateCluster();
        }
        if (smartCluster.getRegistered() == true){
            log.info("Cluster already registered in the system.");
            return smartCluster;
        }

        String url = mirroringServerUrl + REGISTER_CLUSTER_API;
        HttpEntity<SmartCluster> httpEntity = new HttpEntity<>(smartCluster);
        SmartCluster registeredSmartCluster = restTemplate.postForObject(url, httpEntity, SmartCluster.class);
        smartCluster.setIdSmartCluster(registeredSmartCluster.getIdSmartCluster());
        smartCluster.setRegistered(true);

        smartClusterService.update(smartCluster);
        log.info("New cluster registered in cloud successfully cluster:{}", smartCluster);

        return smartCluster;
    }

}
