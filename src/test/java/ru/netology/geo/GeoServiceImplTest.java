package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeoServiceImplTest {
    GeoService geoService = new GeoServiceImpl();

    @ParameterizedTest
    @MethodSource("ipAddresses")
    void testByIp(String ip, Location expectedLocation) {
        Assertions.assertEquals(expectedLocation,geoService.byIp(ip));
    }
    private static Stream<Arguments> ipAddresses() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("192.168.0.1",null),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.64.16", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.0.0.1", new Location("New York", Country.USA, null,  0))
        );
    }

    @Test
    void byCoordinates() throws RuntimeException {
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(10.0, 10.0);
        });
        assertEquals("Not implemented", exception.getMessage());
    }
}