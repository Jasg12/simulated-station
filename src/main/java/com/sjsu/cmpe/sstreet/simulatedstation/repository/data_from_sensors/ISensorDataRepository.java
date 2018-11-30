package com.sjsu.cmpe.sstreet.simulatedstation.repository.data_from_sensors;

import com.sjsu.cmpe.sstreet.simulatedstation.model.Sensor;
import com.sjsu.cmpe.sstreet.simulatedstation.model.SensorData;
import com.sjsu.cmpe.sstreet.simulatedstation.model.SensorValue;
import org.springframework.data.repository.CrudRepository;

public interface ISensorDataRepository {

    SensorData getSensorData(Sensor sensor);
    SensorValue getTemperatureValue();
    SensorValue getWindDirectionValue();
}
