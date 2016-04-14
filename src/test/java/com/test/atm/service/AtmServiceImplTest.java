package com.test.atm.service;

import com.test.atm.AtmApplication;
import com.test.atm.dto.atm.AtmAddress;
import com.test.atm.dto.atm.AtmGeoLocation;
import com.test.atm.dto.ing.Address;
import com.test.atm.dto.ing.GeoLocation;
import com.test.atm.dto.ing.IngAtm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created on 13.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtmApplication.class)
public class AtmServiceImplTest {
    @Autowired
    private AtmService atmService;

    @Test
    public void testConvertAtms() {
        Collection<IngAtm> ingAtms = new LinkedList<>();

        //let it be same GeoLocation for all instances
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setLongitude(1.1);
        geoLocation.setLatitude(1.1);

        Address address1 = new Address();
        address1.setCity("ZAANDAM");
        address1.setHouseNumber("302");
        address1.setPostalCode("3112 BT");
        address1.setStreet("Rotterdamsedijk");
        address1.setGeoLocation(geoLocation);
        IngAtm atm1 = new IngAtm();
        atm1.setAddress(address1);
        ingAtms.add(atm1);

        Address address2 = new Address();
        address2.setCity("ZAANDAM");
        address2.setHouseNumber("303");
        address2.setPostalCode("3112 BT");
        address2.setStreet("Rotterdamsedijk");
        address2.setGeoLocation(geoLocation);
        IngAtm atm2 = new IngAtm();
        atm2.setAddress(address2);
        ingAtms.add(atm2);

        Address address3 = new Address();
        address3.setCity("AMSTERDAM");
        address3.setHouseNumber("304");
        address3.setPostalCode("3112 BT");
        address3.setStreet("Rotterdamsedijk");
        address3.setGeoLocation(geoLocation);
        IngAtm atm3 = new IngAtm();
        atm3.setAddress(address3);
        ingAtms.add(atm3);

        Address address4 = new Address();
        address4.setCity("AMSTERDAM");
        address4.setHouseNumber("305");
        address4.setPostalCode("3112 BT");
        address4.setStreet("Rotterdamsedijk");
        address4.setGeoLocation(geoLocation);
        IngAtm atm4 = new IngAtm();
        atm4.setAddress(address4);
        ingAtms.add(atm4);

        Collection<AtmAddress> convertedAddresses = atmService.toAtmAddress(ingAtms);

        assertNotNull(convertedAddresses);

        assertEquals(4, convertedAddresses.size());

        assertTrue(convertedAddresses.stream().allMatch(atm -> !atm.getCity().isEmpty()));
        assertTrue(convertedAddresses.stream().allMatch(atm -> atm.getGeoLocation() != null));
        assertTrue(convertedAddresses.stream().allMatch(atm -> atm.getStreet().equals("Rotterdamsedijk")));
        assertTrue(convertedAddresses.stream().allMatch(atm -> atm.getPostalCode().equals("3112 BT")));

        assertTrue(convertedAddresses.stream().anyMatch(atm -> atm.getHouseNumber().equals("302")));
        assertTrue(convertedAddresses.stream().anyMatch(atm -> atm.getHouseNumber().equals("303")));
        assertTrue(convertedAddresses.stream().anyMatch(atm -> atm.getHouseNumber().equals("304")));
        assertTrue(convertedAddresses.stream().anyMatch(atm -> atm.getHouseNumber().equals("305")));
    }

    @Test
    public void testFilterAtms() {
        String city = "ZAANDAM";
        Collection<AtmAddress> addresses = new LinkedList<>();

        //let it be same GeoLocation for all instances
        AtmGeoLocation geoLocation = new AtmGeoLocation();
        geoLocation.setLongitude(1.1);
        geoLocation.setLatitude(1.1);

        AtmAddress address1 = new AtmAddress();
        //the city is expected one. This ATm should be found in response.
        address1.setCity(city);
        address1.setHouseNumber("302");
        address1.setPostalCode("3112 BT");
        address1.setStreet("Rotterdamsedijk");
        address1.setGeoLocation(geoLocation);
        addresses.add(address1);

        AtmAddress address2 = new AtmAddress();
        //the city is different. This ATm should NOT be found in response.
        address2.setCity(city + "A");
        address2.setHouseNumber("303");
        address2.setPostalCode("3112 BT");
        address2.setStreet("Rotterdamsedijk");
        address2.setGeoLocation(geoLocation);
        addresses.add(address2);

        AtmAddress address3 = new AtmAddress();
        //the city is expected one. This ATm should be found in response.
        address3.setCity(city);
        address3.setHouseNumber("304");
        address3.setPostalCode("3112 BT");
        address3.setStreet("Rotterdamsedijk");
        address3.setGeoLocation(geoLocation);
        addresses.add(address3);

        AtmAddress address4 = new AtmAddress();
        //the city is different. This ATm should NOT be found in response.
        address4.setCity("AMSTERDAM");
        address4.setHouseNumber("305");
        address4.setPostalCode("3112 BT");
        address4.setStreet("Rotterdamsedijk");
        address4.setGeoLocation(geoLocation);
        addresses.add(address4);

        Collection<AtmAddress> filtered = atmService.filterAtmsByCity(addresses, city);

        assertNotNull(filtered);

        assertEquals(2, filtered.size());

        assertTrue(filtered.stream().allMatch(atm -> atm.getCity().equals(city)));

        assertTrue(filtered.stream().filter(atm -> atm.getHouseNumber().equals("302")).findFirst().isPresent());
        assertTrue(filtered.stream().filter(atm -> atm.getHouseNumber().equals("304")).findFirst().isPresent());

        assertFalse(filtered.stream().filter(atm -> atm.getHouseNumber().equals("303")).findFirst().isPresent());
        assertFalse(filtered.stream().filter(atm -> atm.getHouseNumber().equals("305")).findFirst().isPresent());
    }
}