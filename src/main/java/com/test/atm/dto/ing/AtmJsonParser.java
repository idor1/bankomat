package com.test.atm.dto.ing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 13.02.2016.
 */
@Component
public class AtmJsonParser {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Collection<IngAtm> parseAtms(String atmsString) throws IOException {
        IngAtm[] atms = objectMapper.readValue(atmsString, IngAtm[].class);
        return Arrays.asList(atms);
    }
}
