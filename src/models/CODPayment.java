package models;

public class CODPayment implements PaymentMethod {
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Order placed. Payment of Rs. " + amount + " to be collected on delivery");
        return true;
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Refund of Rs. " + amount + " will be returned to customer");
    }

    @Override
    public String getPaymentType() {
        return "Cash on Delivery";
    }
}
