package com.santander.testesantander.exception;

public class ContaDuplicadaException extends RuntimeException {

    public ContaDuplicadaException(String message) {
        super(message);
    }

    public ContaDuplicadaException(String message, Throwable cause) {
        super(message, cause);
    }
}