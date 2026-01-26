package com.example.payment;

public class PaymentProcessor {

    private static final String API_KEY = "sk_test_123456";
    private final PaymentApiClient paymentApiClient;
    private final PaymentRepository paymentRepository;
    private final EmailSender emailSender;

    public PaymentProcessor(PaymentApiClient paymentApiClient,
                            PaymentRepository paymentRepository,
                            EmailSender emailSender) {
        this.paymentApiClient = paymentApiClient;
        this.paymentRepository = paymentRepository;
        this.emailSender = emailSender;
    }



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