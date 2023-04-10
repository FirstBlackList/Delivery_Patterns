package ru.netology.delivery_2;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    public static String generateDate(int addDays, String formatDate) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(formatDate));
    }

    public static String generateName(String value) {
        Faker faker = new Faker(new Locale(value));
        return faker.name().fullName();
    }

    public static String generateCity() {
        String[] randomCity = new String[]{"Нижний Новгород", "Великий Новгород", "Новосибирск", "Москва", "Санкт-Петербург",
                "Краснодар", "Калининград", "Рязань", "Омск", "Оренбург", "Орёл", "Пенза", "Псков",
                "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск",
                "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск"};
        return randomCity[new Random().nextInt(randomCity.length)];
    }

    public static String generatePhone(String value) {
        Faker faker = new Faker(new Locale(value));
        return faker.phoneNumber().phoneNumber();
    }

    public static class UserRegistration {
        public static InformationAboutUserData generateUser(String value) {
            return new InformationAboutUserData(generateName(value), generateCity(), generatePhone(value));
        }
    }

}