package com.sjsu.cmpe.sstreet.simulatedstation.service;

import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartCluster;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MirroringServerService {

    private final String CLUSTER_UPDATE_API = "/smart_cluster/update";

    private Logger log;
    private RestTemplate restTemplate;

    @Value(value = "${mirroring.server.url}")
    private String mirroringServerUrl;

    @Autowired
    public MirroringServerService(Logger log, RestTemplate restTemplate) {

        this.log = log;
        this.restTemplate = restTemplate;
    }

    public void updateCluster(SmartCluster smartCluster){

        log.info("Update cluster in Mirroring server");
        String url = mirroringServerUrl + CLUSTER_UPDATE_API;
        HttpEntity<SmartCluster> httpEntity = new HttpEntity<>(smartCluster);
        restTemplate.put(url, httpEntity);
    }

}
