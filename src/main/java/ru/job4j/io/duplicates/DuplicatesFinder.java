package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<FileProperty, List<Path>> resultMap = visitor.getfilesByProperty();
        for (Map.Entry<FileProperty, List<Path>> entry : resultMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                List<Path> listPaths = entry.getValue();
                System.out.println(entry.getKey().getName() + " - " + (double) (entry.getKey().getSize() / 1024) + " КБ");
                for (Path item : listPaths) {
                    System.out.println(item.toAbsolutePath());
                }
            }
        }
    }
}