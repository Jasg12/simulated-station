package com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql;


import com.sjsu.cmpe.sstreet.simulatedstation.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SmartNodeRepository extends CrudRepository<SmartNode, Integer> {

    SmartNode findByLocation(Location location);

    List<SmartNode> findBySmartCluster(SmartCluster smartCluster);

    Optional<SmartNode> findByName(String name);

    void deleteByName(String name);

    void deleteBySmartCluster(SmartCluster smartCluster);

    List<SmartNode> findAllByRegistered(Boolean registered);

}
