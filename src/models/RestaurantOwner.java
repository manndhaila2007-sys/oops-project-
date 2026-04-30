package models;

import java.util.*;

public class RestaurantOwner extends User {
    private int restaurantId;
    private double earnings;

    public RestaurantOwner(int userId, String name, String email, String phone, String address, String password, int restaurantId) {
        super(userId, name, email, phone, address, password);
        this.restaurantId = restaurantId;
        this.earnings = 0.0;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("=== RESTAURANT OWNER INFO ===");
        System.out.println("ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Restaurant ID: " + restaurantId);
        System.out.println("Total Earnings: Rs. " + earnings);
    }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public double getEarnings() { return earnings; }
    public void addEarnings(double amount) { this.earnings += amount; }
}
