package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            int read;
            int result = 0;
            boolean makingNumber = false;
            while ((read = input.read()) != -1) {
                if (read >= '0' && read <= '9') {
                    makingNumber = true;
                    result = result * 10 + (read - '0');
                } else {
                    if (makingNumber) {
                        checkEven(result);
                    }
                    result = 0;
                    makingNumber = false;
                }
            }
            if (makingNumber) {
                checkEven(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkEven(int number) {
        if (number % 2 == 0) {
            System.out.println("Число " + number + " является четным");
        } else {
            System.out.println("Число " + number + " не является четным");
        }
    }
}