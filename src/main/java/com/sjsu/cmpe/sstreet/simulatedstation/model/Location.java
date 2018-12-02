package com.sjsu.cmpe.sstreet.simulatedstation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Integer internalId;

    private Integer IdLocation;

    private double longitude;

    private double latitude;

    private String state;

    private String city;

    private String street;

    private Integer zipCode;

    public Location() {

    }

    public Location(Integer idLocation, double longitude, double latitude, String state, String city, String street, Integer zipCode) {

        IdLocation = idLocation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Location(double longitude, double latitude, String state, String city, String street, Integer zipCode) {

        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Location(String state, String city, String street) {

        this.state = state;
        this.city = city;
        this.street = street;
    }

    public Integer getIdLocation() {

        return IdLocation;
    }

    public void setIdLocation(Integer idLocation) {

        IdLocation = idLocation;
    }

    public double getLongitude() {

        return longitude;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getStreet() {

        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }

    public Integer getZipCode() {

        return zipCode;
    }

    public void setZipCode(Integer zipCode) {

        this.zipCode = zipCode;
    }

    public Integer getInternalId() {

        return internalId;
    }

    public void setInternalId(Integer internalId) {

        this.internalId = internalId;
    }
}
