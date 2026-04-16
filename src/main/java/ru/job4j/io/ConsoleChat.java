package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        Random rand = new Random();
        boolean run = true;
        boolean speak = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (run) {
            String line = reader.readLine();
            log.add("User: " + line);
            if (line.equals(OUT)) {
                run = false;
                continue;
            }
            if (line.equals(STOP)) {
                speak = false;
                continue;
            }
            if (line.equals(CONTINUE)) {
                speak = true;
                continue;
            }
            if (speak) {
                String randomElement = botPhrases.get(rand.nextInt(botPhrases.size()));
                log.add("bot: " + randomElement);
                System.out.println(randomElement);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> botPhrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = br.readLine()) != null) {
                botPhrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botPhrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            for (String line : log) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ConsoleChat consoleChat = new ConsoleChat("data/botLog.txt", "data/botPhrases.txt");
        consoleChat.run();
    }
}