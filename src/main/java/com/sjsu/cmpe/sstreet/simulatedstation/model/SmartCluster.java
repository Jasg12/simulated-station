package com.sjsu.cmpe.sstreet.simulatedstation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "smart_cluster")
public class SmartCluster {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Integer simulatedId;

    private String serialNumber;

    private Integer idSmartCluster;

    @JsonIgnore
    private Boolean registered = false;

    private String name;

    private String model;

    private String make;

    private Date installationDate;

    private String url;

    @Transient
    private List<SmartNode> nodes;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="location_idlocation", unique= true, nullable=true, insertable=true, updatable=true)
    private Location location;

    public SmartCluster() {

    }

    public Integer getIdSmartCluster() {

        return idSmartCluster;
    }

    public void setIdSmartCluster(Integer idSmartCluster) {

        this.idSmartCluster = idSmartCluster;
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


    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public List<SmartNode> getNodes() {

        return nodes;
    }

    public void setNodes(List<SmartNode> nodes) {

        this.nodes = nodes;
    }

    public Integer getSimulatedId() {

        return simulatedId;
    }

    public void setSimulatedId(Integer simulatedId) {

        this.simulatedId = simulatedId;
    }

    public String getSerialNumber() {

        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {

        this.serialNumber = serialNumber;
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
            .append("    serialNumber:" + serialNumber + "\n")
            .append("    idSmartCluster:" + idSmartCluster + "\n")
            .append("    registered:" + registered + "\n")
            .append("    name:" + name + "\n")
            .append("    model:" + model + "\n")
            .append("    make:" + make + "\n")
            .append("    installationDate:" + installationDate + "\n")
            .append("    url:" + url + "\n")
            .append("}" + "\n");

        return sb.toString();
    }
}
