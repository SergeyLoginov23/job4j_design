package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int inputSize;
    int outputSize;

    public T poll() {
        if (inputSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outputSize <= inputSize) {
            for (int i = 0; i <= inputSize; i++) {
                output.push(input.pop());
                outputSize++;
                inputSize--;
            }
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        inputSize++;
    }
}