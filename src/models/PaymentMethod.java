package models;

public interface PaymentMethod {
    boolean processPayment(double amount);
    void refundPayment(double amount);
    String getPaymentType();
}
