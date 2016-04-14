package com.test.atm.rest;

import com.test.atm.dto.atm.AtmAddress;
import com.test.atm.dto.ing.AtmJsonParser;
import com.test.atm.dto.ing.IngAtm;
import com.test.atm.service.AtmService;
import com.test.atm.service.ing.IngCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * Created on 13.02.2016.
 */
@RestController
public class AtmController {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AtmController.class);

    @Autowired
    private AtmService atmService;

    @Autowired
    private AtmJsonParser parser;

    @Autowired
    private IngCommunicationService ingCommunication;

    @RequestMapping("/atms")
    public ResponseEntity<Collection<AtmAddress>> getAtmList() throws URISyntaxException, IOException {
        logger.info("All the ATMS about to be shown.");

        String atmsString = ingCommunication.getValidAtmJson();

        Collection<IngAtm> parsed = parser.parseAtms(atmsString);

        Collection<AtmAddress> atms = atmService.toAtmAddress(parsed);

        return ResponseEntity.ok().body(atms);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/atms/{city}")
    public ResponseEntity<Collection<AtmAddress>> getCityAtmList(@PathVariable(value = "city") String city) {
        logger.info("Incoming request for city name: " + city);

        String atmsString = ingCommunication.getValidAtmJson();

        Collection<IngAtm> parsed;
        try {
            parsed = parser.parseAtms(atmsString);
        } catch (IOException e) {
            return new ResponseEntity("ING Service is temporary unavailable or sending the wrong data.",
                    HttpStatus.SERVICE_UNAVAILABLE);
        }

        Collection<AtmAddress> atms = atmService.toAtmAddress(parsed);

        return ResponseEntity.ok().body(atmService.filterAtmsByCity(atms, city));
    }
}
