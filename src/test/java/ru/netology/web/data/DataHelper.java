package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class NameOfTheCity {
        private String name;
    }

    public static NameOfTheCity getNameOfTheCity(String locale) {
     Faker faker = new Faker(new Locale("ru"));
     return new NameOfTheCity(
             faker.address().cityName()
     );
    }
}
