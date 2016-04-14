package com.test.atm.service.ing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 13.02.2016.
 */
@Service
public class IngCommunicationServiceImpl implements IngCommunicationService {
    @Value("${atm.locator.url}")
    private String atmLocatorUrl;

    @Override
    public String getValidAtmJson() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(atmLocatorUrl, String.class);

        return response.substring(response.indexOf("\n") + 1);
    }
}
