package ru.job4j.io;

import ru.job4j.io.duplicates.FileProperty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (Path path : sources) {
                File file = path.toFile();
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        validate(argsName);
        Path pathProj = Paths.get(argsName.get("d"));
        File target = Paths.get(argsName.get("o")).toFile();
        List<Path> sources = Search.search(pathProj, path -> !path.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(sources, target);
    }

    private static void validate(ArgsName argsName) {
        Path pathProj = Paths.get(argsName.get("d"));
        if (!Files.exists(pathProj) || !Files.isDirectory(pathProj)) {
            throw new IllegalArgumentException("Error: Argument d is wrong");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Error: Argument o is wrong");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Error: Argument e is wrong");
        }
    }
}