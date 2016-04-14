package com.test.atm.service;

import com.test.atm.dto.atm.AtmAddress;
import com.test.atm.dto.atm.AtmGeoLocation;
import com.test.atm.dto.ing.IngAtm;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created on 13.02.2016.
 */
@Service
public class AtmServiceImpl implements AtmService {
    @Override
    public Collection<AtmAddress> filterAtmsByCity(Collection<AtmAddress> toFilter, String city) {
        return toFilter.stream().filter(atmAddress -> atmAddress.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
    }

    @Override
    public Collection<AtmAddress> toAtmAddress(Collection<IngAtm> ingAtms) {
        return ingAtms.parallelStream().map(IngAtm::getAddress).map(address -> {
            AtmAddress atmAddress = new AtmAddress();
            atmAddress.setPostalCode(address.getPostalCode());
            atmAddress.setCity(address.getCity());
            atmAddress.setStreet(address.getStreet());
            atmAddress.setHouseNumber(address.getHouseNumber());

            AtmGeoLocation atmGeoLocation = new AtmGeoLocation();
            atmGeoLocation.setLatitude(address.getGeoLocation().getLatitude());
            atmGeoLocation.setLongitude(address.getGeoLocation().getLongitude());

            atmAddress.setGeoLocation(atmGeoLocation);

            return atmAddress;
        }).collect(Collectors.toList());
    }
}
