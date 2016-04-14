package com.test.atm.service;

import com.test.atm.dto.atm.AtmAddress;
import com.test.atm.dto.ing.IngAtm;

import java.util.Collection;

/**
 * Created on 13.02.2016.
 */
public interface AtmService {
    /**
     * Converts the collection if {@link IngAtm} objects to {@link AtmAddress} collcetion.
     * @param ingAtms collection of {@link IngAtm} to convert.
     * @return collection of converted {@link AtmAddress} objects.
     */
    Collection<AtmAddress> toAtmAddress(Collection<IngAtm> ingAtms);

    /**
     * Filter the collection if {@link AtmAddress} objects to find matches given city only.
     * @param ingAtms collection of {@link IngAtm} to filter
     * @param city city to filter by
     * @return collection of {@link AtmAddress} objects matches the given city
     */
    Collection<AtmAddress> filterAtmsByCity(Collection<AtmAddress> ingAtms, String city);
}
