package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> filesByProperty = new HashMap<>();

    public Map<FileProperty, List<Path>> getfilesByProperty() {
        return filesByProperty;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        if (!filesByProperty.containsKey(fileProperty)) {
            filesByProperty.put(fileProperty, new ArrayList<Path>());
        }
        filesByProperty.get(fileProperty).add(file);
        return super.visitFile(file, attributes);
    }
}