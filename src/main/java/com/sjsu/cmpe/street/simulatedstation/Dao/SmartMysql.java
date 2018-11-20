package com.sjsu.cmpe.street.simulatedstation.Dao;

import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import com.sjsu.cmpe.street.simulatedstation.Entity.Smart;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("Mongodb")
public class SmartMysql implements SmartDao {
    private static Map<Integer, Smart> smarts;

    static {
        smarts = new HashMap<Integer, Smart>() {

            {
                put(1, new Smart(1, "1", 1, "Street 1"));
                put(2, new Smart(2, "2", 2, "Street 2"));
                put(3, new Smart(3, "3", 3, "Street 3"));
                put(4, new Smart(4, "4", 4, "Street 4"));
            }
        };
    }

    @Override
    public Collection<Smart> getAllSmartNodes() {
        return this.smarts.values();
    }

    @Override
    public Smart getSmartNodesById(int id) {
        return this.smarts.get(id);
    }

    @Override
    public void removeSmartNodesById(int id) {
        this.smarts.remove(id);
    }

    @Override
    public void updateSmartNodesById(Smart smart) {
        Smart s = smarts.get(smart.getId());
        s.setInstallationdate(smart.getInstallationdate());
        s.setLocation(smart.getLocation());
        smarts.put(smart.getId(), smart);
    }

    @Override
    public void insertSmartNodesById(Smart smart) {
        this.smarts.put(smart.getId(), smart);
    }

}

