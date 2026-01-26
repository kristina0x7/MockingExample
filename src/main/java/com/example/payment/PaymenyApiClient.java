package com.example.payment;

public interface PaymenyApiClient {
    PaymentApiResponse charge(double amount);
}
