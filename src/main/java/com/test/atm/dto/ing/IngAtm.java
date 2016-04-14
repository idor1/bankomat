package com.test.atm.dto.ing;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created on 13.02.2016.
 */
public class IngAtm {
    @JsonProperty("address")
    private Address address;
    @JsonProperty("distance")
    private Integer distance;
    @JsonProperty("type")
    private String type;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "IngAtm{" +
                "address=" + address +
                ", distance=" + distance +
                ", type='" + type + '\'' +
                '}';
    }
}
