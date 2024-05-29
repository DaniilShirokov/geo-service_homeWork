package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    LocalizationService localizationService = new LocalizationServiceImpl();

    @ParameterizedTest
    @EnumSource(Country.class)
    void testLocale(Country country) {
        if (country.equals(Country.RUSSIA)) {
            Assertions.assertEquals("Добро пожаловать", localizationService.locale(country));
        } else {
            Assertions.assertEquals("Welcome", localizationService.locale(country));
        }
    }
}