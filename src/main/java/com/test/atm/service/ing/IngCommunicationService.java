package com.test.atm.service.ing;

/**
 * Created on 13.02.2016.
 */
public interface IngCommunicationService {
    /**
     * Return valid JSON with ATMs description from ING endpoint.
     * @return JSON string.
     */
    String getValidAtmJson();
}
