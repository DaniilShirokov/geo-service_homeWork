package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeoServiceImplTest {
  GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
//  Mockito.when(geoService.byIp("127.0.0.1")).thenReturn(new Location(null,null,null,0));

//    @ParameterizedTest
//    @CsvSource( value = {
//          "127.0.0.1", new Location(null,null,null,0),
//            "192.168.0.1",
//            "172.0.32.11",
//            "96.44.183.149",
//            "172.0.64.16",
//            "96.0.0.1"
//   })
//    void testbyIp(String ip , Location expected) {
//        Assertions.assertEquals(expected,geoService.byIp(ip));
//
//    }
//    @ParameterizedTest
//    @CsvSource( value = {
//
//    }
    @Test
    void byCoordinates()  throws RuntimeException {
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(10.0, 10.0);
        });
        assertEquals("Not supported", exception.getMessage());
    }
}