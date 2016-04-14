package com.test.atm.dto.ing;

import com.test.atm.AtmApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AtmApplication.class)
public class AtmJsonParserTest {
    @Autowired
    private AtmJsonParser parser;

    @Test
    public void testParseAtms() throws IOException {
        String atmsString = "[{\"address\":{\"street\":\"Rotterdamsedijk\",\"housenumber\":\"302\",\"postalcode\":" +
                "\"3112 BT\",\"city\":\"SCHIEDAM\",\"geoLocation\":{\"lat\":\"51.915016\",\"lng\":\"4.402716\"}}," +
                "\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Prinssenstraat\",\"housenumber\":\"52\"," +
                "\"postalcode\":\"6372 EX\",\"city\":\"LANDGRAAF\",\"geoLocation\":{\"lat\":\"50.894201\",\"lng\":" +
                "\"6.015714\"}},\"distance\":0,\"type\":\"ALBERT_HEIJN\"},{\"address\":{\"street\":\"Leidseplein\"," +
                "\"housenumber\":\"1-3\",\"postalcode\":\"1017 PR\",\"city\":\"AMSTERDAM\",\"geoLocation\":{\"lat\":" +
                "\"52.364375\",\"lng\":\"4.883594\"}},\"distance\":0,\"type\":\"ING\"}]";
        Collection<IngAtm> ingAtms = parser.parseAtms(atmsString);

        assertNotNull(ingAtms);
        assertEquals(3, ingAtms.size());

        Iterator<IngAtm> ingAtmIterator = ingAtms.iterator();

        IngAtm ingAtm = ingAtmIterator.next();
        assertNotNull(ingAtm);
        assertNotNull(ingAtm.getAddress());
        assertNotNull(ingAtm.getDistance());
        assertNotNull(ingAtm.getType());

        assertEquals("Rotterdamsedijk", ingAtm.getAddress().getStreet());
        assertEquals("302", ingAtm.getAddress().getHouseNumber());
        assertEquals("3112 BT", ingAtm.getAddress().getPostalCode());
        assertEquals("SCHIEDAM", ingAtm.getAddress().getCity());

        assertEquals(Integer.valueOf(0), ingAtm.getDistance());
        assertEquals("ING", ingAtm.getType());

        assertEquals(Double.valueOf(51.915016), ingAtm.getAddress().getGeoLocation().getLatitude());
        assertEquals(Double.valueOf(4.402716), ingAtm.getAddress().getGeoLocation().getLongitude());

        ingAtm = ingAtmIterator.next();
        assertNotNull(ingAtm);
        assertNotNull(ingAtm.getAddress());
        assertNotNull(ingAtm.getDistance());
        assertNotNull(ingAtm.getType());

        assertEquals("Prinssenstraat", ingAtm.getAddress().getStreet());
        assertEquals("52", ingAtm.getAddress().getHouseNumber());
        assertEquals("6372 EX", ingAtm.getAddress().getPostalCode());
        assertEquals("LANDGRAAF", ingAtm.getAddress().getCity());

        assertEquals(Integer.valueOf(0), ingAtm.getDistance());
        assertEquals("ALBERT_HEIJN", ingAtm.getType());

        assertEquals(Double.valueOf(50.894201), ingAtm.getAddress().getGeoLocation().getLatitude());
        assertEquals(Double.valueOf(6.015714), ingAtm.getAddress().getGeoLocation().getLongitude());

        ingAtm = ingAtmIterator.next();
        assertNotNull(ingAtm);
        assertNotNull(ingAtm.getAddress());
        assertNotNull(ingAtm.getDistance());
        assertNotNull(ingAtm.getType());

        assertEquals("Leidseplein", ingAtm.getAddress().getStreet());
        assertEquals("1-3", ingAtm.getAddress().getHouseNumber());
        assertEquals("1017 PR", ingAtm.getAddress().getPostalCode());
        assertEquals("AMSTERDAM", ingAtm.getAddress().getCity());

        assertEquals(Integer.valueOf(0), ingAtm.getDistance());
        assertEquals("ING", ingAtm.getType());

        assertEquals(Double.valueOf(52.364375), ingAtm.getAddress().getGeoLocation().getLatitude());
        assertEquals(Double.valueOf(4.883594), ingAtm.getAddress().getGeoLocation().getLongitude());
    }
}