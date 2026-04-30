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
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Payment amount must be greater than zero.");
            }
            if (!isValidCard()) {
                throw new SecurityException("Invalid card details provided.");
            }
            if (balance < amount) {
                throw new IllegalStateException("Insufficient funds on card.");
            }
            
            balance -= amount;
            System.out.println("Payment of Rs. " + amount + " processed via Card ending in " + 
                             cardNumber.substring(cardNumber.length() - 4));
            return true;
            
        } catch (IllegalArgumentException | SecurityException | IllegalStateException e) {
            System.err.println("Payment Processing Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during payment: " + e.getMessage());
            return false;
        }
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
