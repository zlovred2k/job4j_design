package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn = 0;
    private int countOut = 0;

    public T poll() {

        if (countIn == 0 && countOut == 0) {
            throw new NoSuchElementException();
        }
        if (countOut == 0) {
            while (countIn > 0) {
                out.push(in.pop());
                countIn--;
                countOut++;
            }
        }
        countOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        countIn++;
    }
}
