package com.sjsu.cmpe.sstreet.simulatedstation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "smart_node")
public class SmartNode {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer internalId;

    private Integer idSmartNode;

    private String name;

    private String model;

    private String make;

    private Date installationDate;

    private Boolean registered = false;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="location_idlocation", unique= true, nullable=true, insertable=true, updatable=true)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "smart_cluster_idSmartCluster")
    private SmartCluster smartCluster;

    @Transient
    private List<Sensor> sensors;

    public SmartNode(
        String name,
        String model,
        String make,
        Date installationDate,
        Location location,
        SmartCluster smartCluster
    ) {

        this.name = name;
        this.model = model;
        this.make = make;
        this.installationDate = installationDate;
        this.location = location;
        this.smartCluster = smartCluster;
    }

    public SmartNode(String name, String model, String make, Date installationDate, Location location) {

        this.name = name;
        this.model = model;
        this.make = make;
        this.installationDate = installationDate;
        this.location = location;
    }

    public SmartNode() {

    }

    public Integer getIdSmartNode() {

        return idSmartNode;
    }

    public void setIdSmartNode(Integer idSmartNode) {

        this.idSmartNode = idSmartNode;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getModel() {

        return model;
    }

    public void setModel(String model) {

        this.model = model;
    }

    public String getMake() {

        return make;
    }

    public void setMake(String make) {

        this.make = make;
    }

    public Date getInstallationDate() {

        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {

        this.installationDate = installationDate;
    }

    public Location getLocation() {

        return location;
    }

    public void setLocation(Location location) {

        this.location = location;
    }

    public SmartCluster getSmartCluster() {

        return smartCluster;
    }

    public void setSmartCluster(SmartCluster smartCluster) {

        this.smartCluster = smartCluster;
    }

    public List<Sensor> getSensors() {

        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {

        this.sensors = sensors;
    }

    public Integer getInternalId() {

        return internalId;
    }

    public void setInternalId(Integer internalId) {

        this.internalId = internalId;
    }

    public Boolean getRegistered() {

        return registered;
    }

    public void setRegistered(Boolean registered) {

        this.registered = registered;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb
            .append("\n")
            .append("{" + "\n")
            .append("    idSmartNode:" + idSmartNode + "\n")
            .append("    name:" + name + "\n")
            .append("    model:" + model + "\n")
            .append("    make:" + make + "\n")
            .append("    registered:" + registered + "\n")
            .append("    installationDate:" + installationDate + "\n")
            .append("    locationId:" + (location != null?location.getIdLocation():"null") + "\n")
            .append("    clusterId:" + (smartCluster != null?smartCluster.getIdSmartCluster():"null") + "\n")
            .append("    sensors:" + (sensors != null?sensors.size():0) + "\n")
            .append("}" + "\n");

        return sb.toString();
    }
}
