package com.epam.university.java.core.task033;

public class Task033Impl implements Task033 {
    @Override
    public void doSomething(int first, int second) {

        if (first > second) {
            throw new GreaterExceptionImpl();
        }
        if (first < second) {
            throw new LessExceptionImpl();
        }
        if (second == 0) {
            throw new ArithmeticException();
        }
    }
}
