package models;

import java.util.*;

public class Customer extends User {
    private List<Integer> orderIds;
    private double walletBalance;

    public Customer(int userId, String name, String email, String phone, String address, String password) {
        super(userId, name, email, phone, address, password);
        this.orderIds = new ArrayList<>();
        this.walletBalance = 0.0;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("=== CUSTOMER INFO ===");
        System.out.println("ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Wallet Balance: Rs. " + walletBalance);
        System.out.println("Total Orders: " + orderIds.size());
    }

    public void addOrder(int orderId) {
        orderIds.add(orderId);
    }

    public List<Integer> getOrderIds() { return new ArrayList<>(orderIds); }
    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double balance) { this.walletBalance = balance; }
    public void addToWallet(double amount) { this.walletBalance += amount; }
    public void deductFromWallet(double amount) { 
        if (this.walletBalance >= amount) {
            this.walletBalance -= amount;
        }
    }
}
