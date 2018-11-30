package com.sjsu.cmpe.sstreet.simulatedstation.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idSensor;

    private String name;

    private String model;

    private String make;

    private Date installationDate;

    private String type;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="location_idlocation", unique= true, nullable=true, insertable=true, updatable=true)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "smart_node_idSmartNode")
    private SmartNode smartNode;

    public Sensor(
        String name,
        String model,
        String make,
        Date installationDate,
        String type,
        Location location,
        SmartNode smartNode
    ) {

        this.name = name;
        this.model = model;
        this.make = make;
        this.installationDate = installationDate;
        this.type = type;
        this.location = location;
        this.smartNode = smartNode;
    }

    public Sensor(String name, String model, String make, Date installationDate, String type, Location location) {

        this.name = name;
        this.model = model;
        this.make = make;
        this.installationDate = installationDate;
        this.type = type;
        this.location = location;
    }

    public Sensor() {

    }

    public Integer getIdSensor() {

        return idSensor;
    }

    public void setIdSensor(Integer idSensor) {

        this.idSensor = idSensor;
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

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public Location getLocation() {

        return location;
    }

    public void setLocation(Location location) {

        this.location = location;
    }

    public SmartNode getSmartNode() {

        return smartNode;
    }

    public void setSmartNode(SmartNode smartNode) {

        this.smartNode = smartNode;
    }
}
