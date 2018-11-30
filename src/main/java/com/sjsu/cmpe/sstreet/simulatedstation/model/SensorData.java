package com.sjsu.cmpe.sstreet.simulatedstation.model;

public class SensorData {

    private Integer idSmartCluster;

    private Integer idSmartNode;

    private Integer idSensor;

    private SensorType type;

    private Long timestamp;

    private SensorValue value;

    public SensorData(
        Integer idSmartCluster,
        Integer idSmartNode,
        SensorType type,
        Long timestamp,
        Integer idSensor,
        SensorValue value
    ) {

        this.idSmartCluster = idSmartCluster;
        this.idSmartNode = idSmartNode;
        this.type = type;
        this.timestamp = timestamp;
        this.idSensor = idSensor;
        this.value = value;
    }

    public SensorData() {

    }

    public Integer getIdSmartCluster() {

        return idSmartCluster;
    }

    public void setIdSmartCluster(Integer idSmartCluster) {

        this.idSmartCluster = idSmartCluster;
    }

    public Integer getIdSmartNode() {

        return idSmartNode;
    }

    public void setIdSmartNode(Integer idSmartNode) {

        this.idSmartNode = idSmartNode;
    }

    public SensorType getType() {

        return type;
    }

    public void setType(SensorType type) {

        this.type = type;
    }

    public Long getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Long timestamp) {

        this.timestamp = timestamp;
    }

    public Integer getIdSensor() {

        return idSensor;
    }

    public void setIdSensor(Integer idSensor) {

        this.idSensor = idSensor;
    }

    public SensorValue getValue() {

        return value;
    }

    public void setValue(SensorValue value) {

        this.value = value;
    }
}
