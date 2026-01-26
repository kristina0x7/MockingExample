package com.example.payment;

public class PaymentProcessor {
    private static final String API_KEY = "sk_test_123456";

    public boolean processPayment(double amount) {
        // Anropar extern betaltjänst direkt med statisk API-nyckel
        PaymentApiResponse response = PaymentApi.charge(API_KEY, amount);

        // Skriver till databas direkt
        if (response.isSuccess()) {
            DatabaseConnection.getInstance()
                    .executeUpdate("INSERT INTO payments (amount, status) VALUES (" + amount + ", 'SUCCESS')");
        }

        // Skickar e-post direkt
        if (response.isSuccess()) {
            EmailService.sendPaymentConfirmation("user@example.com", amount);
        }

        return response.isSuccess();
    }
}