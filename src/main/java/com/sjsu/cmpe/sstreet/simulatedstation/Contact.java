package com.sjsu.cmpe.sstreet.simulatedstation;

public class Contact {

    private String sensorname;
    private String location;
    private String type;

    public Contact(String Sensorname, String Location, String Type) {

        sensorname = Sensorname;
        location = Location;
        type = Type;
    }

    public String getSensorname() {
        return sensorname;
    }

    public void setSensorname(String sensorname) {
        this.sensorname = sensorname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

