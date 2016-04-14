package com.test.atm.dto.ing;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created on 13.02.2016.
 */
public class GeoLocation {
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("lng")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
