package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> lines404 = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] words = line.trim().split("\\s+");
                if (words.length >= 2) {
                    if (words[words.length - 2].equals("404")) {
                        lines404.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines404;
    }

    public void saveTo(String out) {
        var data = filter();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
