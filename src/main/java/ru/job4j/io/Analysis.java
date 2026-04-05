package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean servUp = true;
        String servDownStart = null;
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
                for (String line = input.readLine(); line != null; line = input.readLine()) {
                    String trimmed = line.trim();
                    String[] words = trimmed.split("\\s+");
                    if (words.length >= 2) {
                        String status = words[0].trim();
                        String time = words[1].trim();
                        if (servUp) {
                            if ("400".equals(status) || "500".equals(status)) {
                                servUp = false;
                                servDownStart = time;
                            }
                        } else {
                            if ("200".equals(status) || "300".equals(status)) {
                                servUp = true;
                                writer.write(servDownStart + ";" + time + ";");
                                writer.newLine();
                                servDownStart = null;

                            }
                        }
                    }
                }
                if (!servUp && servDownStart != null) {
                    writer.write(servDownStart + ";" + "Сервер все еще недоступен! Обратитесь к администратору");
                    writer.newLine();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}