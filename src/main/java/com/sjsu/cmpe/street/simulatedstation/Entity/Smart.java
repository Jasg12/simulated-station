package com.sjsu.cmpe.street.simulatedstation.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="smart")
public class Smart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int installationdate;
    private String location;


    public Smart(int id,String name, int installationdate, String location) {
        this.id = id;
        this.installationdate = installationdate;
        this.location = location;
        this.name = name;
    }
    public Smart(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstallationdate() {
        return installationdate;
    }

    public void setInstallationdate(int installationdate) {
        this.installationdate = installationdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void put(Smart id, Smart smart) {
    }
}