package ru.job4j.serialization.json;

public class Landmark {
    private final String name;
    private final int age;

    public Landmark(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Landmark{"
                + "name='" + name + '\''
                + "age='" + age + '\''
                + '}';
    }
}
