package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final City city = new City("Vladimir", 1140, false,  new Landmark("Golden Gate", 862),
                new String[] {"Lenina", "Pobedy", "Mira"});

        final Gson gson = new GsonBuilder().create();
        final String JsonCity = gson.toJson(city);
        System.out.println(JsonCity);

        final City cityNew = gson.fromJson(JsonCity, City.class);
        System.out.println(cityNew);

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonlandmark = new JSONObject("{\"name\":\"Golden Gate\", \"age\":\"862\"}");
        List<String> list = new ArrayList<>();
        list.add("Lenina");
        list.add("Pobedy");
        list.add("Mira");
        JSONArray jsonStreets = new JSONArray(list);
        jsonObject.put("name", city.getName());
        jsonObject.put("age", city.getAge());
        jsonObject.put("capital", city.isCapital());
        jsonObject.put("landmark", jsonlandmark);
        jsonObject.put("streets", jsonStreets);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(city));
    }
}
