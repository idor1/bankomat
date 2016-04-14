package com.test.atm.dto.ing;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created on 13.02.2016.
 */
public class Address {
    @JsonProperty("street")
    private String street;
    @JsonProperty("housenumber")
    private String houseNumber;
    @JsonProperty("postalcode")
    private String postalCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("geoLocation")
    private GeoLocation geoLocation;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", geoLocation=" + geoLocation +
                '}';
    }
}
