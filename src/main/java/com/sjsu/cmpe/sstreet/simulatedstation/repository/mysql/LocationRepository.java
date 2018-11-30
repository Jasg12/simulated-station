package com.sjsu.cmpe.sstreet.simulatedstation.repository.mysql;

import com.sjsu.cmpe.sstreet.simulatedstation.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {

}
