package com.sjsu.cmpe.street.simulatedstation.service;


import com.sjsu.cmpe.street.simulatedstation.Dao.SmartDao;
import com.sjsu.cmpe.street.simulatedstation.Entity.Smart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SmartNodeService {



        @Autowired
        @Qualifier("mongodb")
        private SmartDao smartDao;


        public Collection<Smart> getAllSmartNodes(){

            return this.smartDao.getAllSmartNodes(); }

        public Smart getSmartNodesById(int id){
            return this.smartDao.getSmartNodesById(id); }


        public void insertSmartNodesById(Smart smart) {
            this.smartDao.insertSmartNodesById(smart);
        }

    public void removeSmartNodesById(int id) { this.smartDao.removeSmartNodesById(id);
    }

    public void updateSmartNodesById(Smart smart) { this.smartDao.updateSmartNodesById(smart);
    }
}
