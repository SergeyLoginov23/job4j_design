package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final City city = new City(false, 30, "Vladimir", new Landmark("Golden Gate", 862),
                new String[] {"Lenina", "Pobedy", "Mira"});

        final Gson gson = new GsonBuilder().create();
        final String JsonCity = gson.toJson(city);
        System.out.println(JsonCity);

        final City cityNew = gson.fromJson(JsonCity, City.class);
        System.out.println(cityNew);
    }
}
