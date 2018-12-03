package com.sjsu.cmpe.sstreet.simulatedstation.service;

import com.sjsu.cmpe.sstreet.simulatedstation.model.Location;
import com.sjsu.cmpe.sstreet.simulatedstation.model.Sensor;
import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartCluster;
import com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql.LocationRepository;
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
    private final String REGISTER_SENSOR_API = "/sensor/create";

    private SmartClusterService smartClusterService;
    private LocationRepository locationRepository;
    private RestTemplate restTemplate;
    private Logger log;
    private SensorService sensorService;

    @Autowired
    public RegisteringService(
        SmartClusterService smartClusterService,
        LocationRepository locationRepository,
        RestTemplate restTemplate,
        Logger log,
        SensorService sensorService)
    {
        this.smartClusterService = smartClusterService;
        this.locationRepository = locationRepository;
        this.restTemplate = restTemplate;
        this.log = log;
        this.sensorService = sensorService;
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
        Location location = smartCluster.getLocation();
        location.setIdLocation(registeredSmartCluster.getLocation().getIdLocation());
        locationRepository.save(location);

        smartClusterService.update(smartCluster);
        log.info("New cluster registered in cloud successfully cluster:{}", smartCluster);

        return smartCluster;
    }

    public Sensor registerSensor(Sensor sensor){
        String url = mirroringServerUrl + REGISTER_SENSOR_API;
        HttpEntity<Sensor> httpEntity = new HttpEntity<>(sensor);
        Sensor registeredSensor = restTemplate.postForObject(url, httpEntity, Sensor.class);
        sensor.setIdSensor(registeredSensor.getIdSensor());
        sensor.setRegistered(true);
        Location location = sensor.getLocation();
        location.setIdLocation(registeredSensor.getLocation().getIdLocation());
        locationRepository.save(location);

        Sensor updatedSensor = sensorService.update(sensor);
        log.info("New sensor registered in cloud successfully sensor:{}", updatedSensor);

        return updatedSensor;
    }
}
