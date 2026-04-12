package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateInput(args);
        Path start = Paths.get(args[0]);
        String searchDetails = args[1];
        search(start, path -> path.toFile().getName().endsWith(searchDetails)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateInput(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("No enough inputs");
        }
        Path start = Paths.get(args[0]);
        String searchDetails = args[1];
        if (!Files.isDirectory(start) || !Files.exists(start)) {
            throw new IllegalArgumentException("Root folder is null or not a directory");
        }
        if (searchDetails.isEmpty()) {
            throw new IllegalArgumentException("No filter for search");
        }
    }
}