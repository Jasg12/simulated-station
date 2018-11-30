package com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql;

import com.sjsu.cmpe.sstreet.simulatedstation.model.*;;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SmartClusterRepository extends CrudRepository<SmartCluster, Integer> {

    SmartCluster findByLocation(Location location);

    Optional<SmartCluster> findByName(String name);

    void deleteByName(String name);


}
