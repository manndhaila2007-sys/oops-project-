package handlers;

import models.*;
import java.util.*;

public class PaymentService {
    private Map<Integer, PaymentMethod> paymentMethods = new HashMap<>();
    private int paymentCounter = 1;

    public int addCardPayment(String cardNumber, String cardholderName, String expiryDate, double balance) {
        CardPayment payment = new CardPayment(cardNumber, cardholderName, expiryDate, balance);
        paymentMethods.put(paymentCounter, payment);
        return paymentCounter++;
    }

    public int addWalletPayment(double initialBalance) {
        WalletPayment payment = new WalletPayment(initialBalance);
        paymentMethods.put(paymentCounter, payment);
        return paymentCounter++;
    }

    public int addCODPayment() {
        CODPayment payment = new CODPayment();
        paymentMethods.put(paymentCounter, payment);
        return paymentCounter++;
    }

    public boolean processPayment(int paymentId, double amount) {
        PaymentMethod payment = paymentMethods.get(paymentId);
        if (payment != null) {
            return payment.processPayment(amount);
        }
        return false;
    }

    public boolean refundPayment(int paymentId, double amount) {
        PaymentMethod payment = paymentMethods.get(paymentId);
        if (payment != null) {
            payment.refundPayment(amount);
            return true;
        }
        return false;
    }

    public PaymentMethod getPaymentMethod(int paymentId) {
        return paymentMethods.get(paymentId);
    }

    public String getPaymentType(int paymentId) {
        PaymentMethod payment = paymentMethods.get(paymentId);
        if (payment != null) {
            return payment.getPaymentType();
        }
        return "Unknown";
    }
}
