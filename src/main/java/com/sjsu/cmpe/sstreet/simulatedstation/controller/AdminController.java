package com.sjsu.cmpe.sstreet.simulatedstation.controller;

import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartCluster;
import com.sjsu.cmpe.sstreet.simulatedstation.model.statistic.ConnectionStatus;
import com.sjsu.cmpe.sstreet.simulatedstation.model.statistic.ConnectivityStat;
import com.sjsu.cmpe.sstreet.simulatedstation.model.statistic.EntityType;
import com.sjsu.cmpe.sstreet.simulatedstation.service.SmartClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class AdminController {

    private SmartClusterService smartClusterService;

    @Autowired
    public AdminController(SmartClusterService smartClusterService) {

        this.smartClusterService = smartClusterService;
    }

    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }

    @RequestMapping(path = "/cluster/status", method = RequestMethod.GET, produces = "application/json")
    public ConnectivityStat getClusterStatus(){
        ConnectivityStat connectivityStat = new ConnectivityStat();
        SmartCluster cluster = smartClusterService.getSmartCluster();
        connectivityStat.setId(cluster.getInternalId());
        connectivityStat.setEntityType(EntityType.SmartCluster);
        connectivityStat.setStatus(ConnectionStatus.UP);

        return connectivityStat;
    }

}
