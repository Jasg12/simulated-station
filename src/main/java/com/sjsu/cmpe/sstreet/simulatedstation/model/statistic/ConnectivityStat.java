package com.sjsu.cmpe.sstreet.simulatedstation.model.statistic;


import javax.persistence.Transient;


public class ConnectivityStat {

    private Integer id;

    private EntityType entityType;

    private ConnectionStatus status;

    private Long timestamp;

    @Transient
    private Object entity;

    public ConnectivityStat() {

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public EntityType getEntityType() {

        return entityType;
    }

    public void setEntityType(EntityType entityType) {

        this.entityType = entityType;
    }

    public ConnectionStatus getStatus() {

        return status;
    }

    public void setStatus(ConnectionStatus status) {

        this.status = status;
    }

    public Long getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Long timestamp) {

        this.timestamp = timestamp;
    }

    public Object getEntity() {

        return entity;
    }

    public void setEntity(Object entity) {

        this.entity = entity;
    }
}
