package com.example.payment;

public class PaymentPersistenceException extends RuntimeException {
    public PaymentPersistenceException(String message) {
        super(message);
    }

    public PaymentPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
