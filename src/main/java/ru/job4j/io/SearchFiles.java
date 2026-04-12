package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    final Predicate<Path> condition;
    final List<Path> fileList;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
        this.fileList = new ArrayList<>();
    }

    public List<Path> getPaths() {
        return new ArrayList<>(fileList);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)   throws IOException {
        if (condition.test(file)) {
            fileList.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

}
