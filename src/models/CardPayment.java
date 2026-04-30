package models;

public class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;
    private double balance;

    public CardPayment(String cardNumber, String cardholderName, String expiryDate, double balance) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.balance = balance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (balance >= amount && isValidCard()) {
            balance -= amount;
            System.out.println("Payment of Rs. " + amount + " processed via Card ending in " + 
                             cardNumber.substring(cardNumber.length() - 4));
            return true;
        }
        return false;
    }

    @Override
    public void refundPayment(double amount) {
        balance += amount;
        System.out.println("Refund of Rs. " + amount + " processed to Card");
    }

    @Override
    public String getPaymentType() {
        return "Card Payment";
    }

    private boolean isValidCard() {
        return cardNumber.length() == 16 && !expiryDate.isEmpty();
    }

    public double getBalance() { return balance; }
}
