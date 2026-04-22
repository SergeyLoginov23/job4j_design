package ru.job4j.serialization.json;

import java.util.Arrays;

public class City {
    private final String name;
    private final int age;
    private final boolean capital;

    private final Landmark landmark;
    private final String[] streets;

    public City(boolean capital, int age, String name, Landmark landmark, String[] streets) {
        this.capital = capital;
        this.age = age;
        this.name = name;
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
