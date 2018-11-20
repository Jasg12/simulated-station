package com.sjsu.cmpe.street.simulatedstation.service;

import com.sjsu.cmpe.street.simulatedstation.Entity.SList;
import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnodeService {
    @Autowired(required = true)
    private final static List<SList> snodeservice = new ArrayList<SList>();

    public Sensor getSensorvalue(int id) {
        return this.getSensorvalue(id);
    }

    public String getStatus(@PathVariable("id") int id) {
        return ("True");
    }

    public Sensor getAllSensorsData(@PathVariable("id") int id) { return this.getAllSensorsData(id);}

    public String getAllSensorNodes(int id) { return this.getAllSensorNodes(id);}
}
