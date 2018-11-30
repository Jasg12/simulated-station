package com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql;

import com.sjsu.cmpe.sstreet.simulatedstation.model.Sensor;
import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartNode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor, Integer> {

    List<Sensor> findBySmartNode(SmartNode smartNode);

    void deleteBySmartNode(SmartNode smartNode);

}
