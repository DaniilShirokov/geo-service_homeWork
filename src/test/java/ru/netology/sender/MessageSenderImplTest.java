package ru.netology.sender;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "127.0.0.1",
            "",
            "172.0.32.11",
            "96.44.183.149",
            "172.0.64.16",
            "96.0.0.1"
    })
    void send(String ip) {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        Location location = new Location("New York", Country.USA, "10th Avenue", 32);
        Mockito.when(geoService.byIp(headers.get(MessageSenderImpl.IP_ADDRESS_HEADER))).thenReturn(location);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(localizationService.locale(Country.GERMANY)).thenReturn("Welcome");

        assertEquals("Welcome", messageSender.send(headers));
    }
}