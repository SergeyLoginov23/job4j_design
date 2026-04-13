package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length;  i++) {
            String trimmed = args[i].trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException("Error: Argument is empty");
            }
            if (!trimmed.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + trimmed + "' does not start with a '-' character");
            }
            if (!trimmed.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + trimmed + "' does not contain an equal sign");
            }
            String[] words = trimmed.split("=", 2);
            String key = words[0].trim();
            key = key.substring(1);
            String value = words[1].trim();
            if (key.isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + trimmed + "' does not contain a key");
            }
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + trimmed + "' does not contain a value");
            }
            if (values.containsKey(key)) {
                throw new IllegalArgumentException("Duplicate key: " + key);
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}