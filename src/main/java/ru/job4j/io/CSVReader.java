package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static final String OUT = "stdout";

    public static void handle(ArgsName argsName) throws Exception {
        Path pathSource = Paths.get(argsName.get("path"));
        String pathDestStr = argsName.get("out");
        String delim = argsName.get("delimiter");
        try (var scanner = new Scanner(pathSource)) {
            String filter =  argsName.get("filter");
            String[] filterColums = filter.trim().split(",");
            String header = scanner.nextLine();
            String[] headerColums = header.trim().split(delim);
            StringBuilder result = new StringBuilder();
            int[] indexes = new int[filterColums.length];
            Map<String, Integer> indexMap = new HashMap<>();

            for (int i = 0; i < headerColums.length; i++) {
                indexMap.put(headerColums[i], i);
            }
            for (int i = 0; i < filterColums.length; i++) {
                if (indexMap.get(filterColums[i]) == null) {
                    throw new IllegalArgumentException("Error: file does not contain a filter column");
                }
                indexes[i] = indexMap.get(filterColums[i]);
            }
            StringJoiner joinerHeader = new StringJoiner(delim);
            for (int i = 0; i < indexes.length; i++) {

                joinerHeader.add(headerColums[indexes[i]]);
            }

            result.append(joinerHeader);
            result.append(System.lineSeparator());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineWords = line.trim().split(delim);
                StringJoiner joinerLine = new StringJoiner(delim);
                for (int i = 0; i < indexes.length; i++) {
                    joinerLine.add(lineWords[indexes[i]]);
                }
                result.append(joinerLine);
                result.append(System.lineSeparator());
            }
            if (!OUT.equals(pathDestStr)) {
                Path pathDest  = Paths.get(argsName.get("out"));
                Files.writeString(pathDest, result);
            } else {
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        validate(argsName);
        handle(argsName);
    }

    private static void validate(ArgsName argsName) {
        Path pathSource = Paths.get(argsName.get("path"));
        if (!Files.exists(pathSource)) {
            throw new IllegalArgumentException("Error: Argument path is wrong");
        }
        if (!OUT.equals(argsName.get("out"))) {
            Path pathDest  = Paths.get(argsName.get("out"));
            if (!Files.exists(pathDest)) {
                throw new IllegalArgumentException("Error: Argument out is wrong");
            }
        }
    }
}