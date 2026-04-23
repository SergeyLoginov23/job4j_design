package ru.job4j.serialization.json;

import java.util.Arrays;

public class City {
    private final String name;
    private final int age;
    private final boolean capital;

    private final Landmark landmark;
    private final String[] streets;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isCapital() {
        return capital;
    }

    public Landmark getLandmark() {
        return landmark;
    }

    public String[] getStreets() {
        return streets;
    }

    public City(String name, int age, boolean capital,   Landmark landmark, String[] streets) {
        this.name = name;
        this.age = age;
        this.capital = capital;
        this.landmark = landmark;
        this.streets = streets;
    }

    @Override
    public String toString() {
        return "City{"
                + "name=" + name
                + ", age=" + age
                + ", capital=" + capital
                + ", landmark=" + landmark
                + ", streets=" + Arrays.toString(streets)
                + '}';
    }
}
