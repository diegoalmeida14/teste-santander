package com.santander.testesantander.exception;

public class SaldoNegativoException extends RuntimeException {

    public SaldoNegativoException(String message) {
        super(message);
    }

    public SaldoNegativoException(String message, Throwable cause) {
        super(message, cause);
    }
}