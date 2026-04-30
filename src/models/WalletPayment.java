package models;

public class WalletPayment implements PaymentMethod {
    private double walletBalance;

    public WalletPayment(double initialBalance) {
        this.walletBalance = initialBalance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (walletBalance >= amount) {
            walletBalance -= amount;
            System.out.println("Payment of Rs. " + amount + " processed via Wallet");
            return true;
        }
        System.out.println("Insufficient wallet balance!");
        return false;
    }

    @Override
    public void refundPayment(double amount) {
        walletBalance += amount;
        System.out.println("Refund of Rs. " + amount + " added to Wallet");
    }

    @Override
    public String getPaymentType() {
        return "Wallet Payment";
    }

    public double getWalletBalance() { return walletBalance; }
    public void addToWallet(double amount) { walletBalance += amount; }
}
