package com.sjsu.cmpe.sstreet.simulatedstation.model;

import java.util.List;

public class SmartClusterData {

    private Integer idSmartCluster;

    private List<SmartNodeData> smartNodeDataList;

    public SmartClusterData(Integer idSmartCluster, List<SmartNodeData> smartNodeDataList) {
        this.idSmartCluster = idSmartCluster;
        this.smartNodeDataList = smartNodeDataList;
    }

    public Integer getIdSmartCluster() {
        return idSmartCluster;
    }

    public void setIdSmartCluster(Integer idSmartCluster) {
        this.idSmartCluster = idSmartCluster;
    }

    public List<SmartNodeData> getSmartNodeDataList() {
        return smartNodeDataList;
    }

    public void setSmartNodeDataList(List<SmartNodeData> smartNodeDataList) {
        this.smartNodeDataList = smartNodeDataList;
    }
}
