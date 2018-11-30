package com.sjsu.cmpe.sstreet.simulatedstation.model;

import java.util.List;

public class SmartNodeData {

    private Integer idSmartNode;

    private List<SensorData> sensorDataList;

    public SmartNodeData(Integer idSmartNode, List<SensorData> sensorDataList) {
        this.idSmartNode = idSmartNode;
        this.sensorDataList = sensorDataList;
    }

    public Integer getIdSmartNode() {
        return idSmartNode;
    }

    public void setIdSmartNode(Integer idSmartNode) {
        this.idSmartNode = idSmartNode;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }
}
