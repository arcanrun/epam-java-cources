package com.epam.university.java.core.task033;

public class GreaterExceptionImpl extends RuntimeException implements GreaterException {

    @Override
    public String getMessage() {
        return "First > Second";
    }

    @Override
    public synchronized Throwable getCause() {
        return new BaseExceptionImpl();
    }
}
