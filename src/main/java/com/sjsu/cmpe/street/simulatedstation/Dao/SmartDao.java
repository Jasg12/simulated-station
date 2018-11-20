package com.sjsu.cmpe.street.simulatedstation.Dao;


import com.sjsu.cmpe.street.simulatedstation.Entity.Smart;

import java.util.Collection;

public interface SmartDao {

    Smart getSmartNodesById(int id);

    void removeSmartNodesById(int id);

    void updateSmartNodesById(Smart smart);

    void insertSmartNodesById(Smart smart);

    Collection<Smart> getAllSmartNodes();
}

