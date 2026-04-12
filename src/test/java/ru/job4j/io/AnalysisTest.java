package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void unavailableTwoTimes(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> result.append(line).append(System.lineSeparator()));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("10:57:01;10:59:01;");
        sb.append(System.lineSeparator());
        sb.append("11:01:02;11:02:02;");
        sb.append(System.lineSeparator());
        String res = sb.toString();
        assertThat(res).hasToString(result.toString());
    }

    @Test
    void unavailableOne(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("300 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> result.append(line).append(System.lineSeparator()));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("10:57:01;11:02:02;");
        sb.append(System.lineSeparator());
        String res = sb.toString();
        assertThat(res).hasToString(result.toString());
    }

    @Test
    void neverAvailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("500 11:03:02");
            output.println("400 11:04:02");
            output.println("500 11:05:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> result.append(line).append(System.lineSeparator()));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("11:03:02;Сервер все еще недоступен! Обратитесь к администратору");
        sb.append(System.lineSeparator());
        String res = sb.toString();
        assertThat(res).hasToString(result.toString());
    }
}