package models;

import java.util.*;
import java.time.LocalDateTime;

public class Order {
    public enum OrderStatus { PENDING, CONFIRMED, PREPARING, READY, DELIVERED, CANCELLED }
    
    private int orderId;
    private int customerId;
    private int restaurantId;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    private double totalPrice;
    private String deliveryAddress;

    public Order(int orderId, int customerId, int restaurantId, String deliveryAddress) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.deliveryAddress = deliveryAddress;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.orderTime = LocalDateTime.now();
        this.totalPrice = 0.0;
    }

    public void addItem(OrderItem item) {
        try {
            if (item == null) {
                throw new IllegalArgumentException("Cannot add a null item to the order.");
            }
            if (status != OrderStatus.PENDING) {
                throw new IllegalStateException("Cannot add items. Order is already " + status);
            }
            items.add(item);
            calculateTotal();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Order Error: " + e.getMessage());
        }
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            calculateTotal();
        }
    }

    private void calculateTotal() {
        totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.getTotal();
        }
    }

    public void updateStatus(OrderStatus newStatus) {
        try {
            if (newStatus == null) {
                throw new IllegalArgumentException("Order status cannot be null.");
            }
            if (this.status == OrderStatus.CANCELLED) {
                throw new IllegalStateException("Cannot update status of a cancelled order.");
            }
            this.status = newStatus;
            if (newStatus == OrderStatus.DELIVERED) {
                this.deliveryTime = LocalDateTime.now();
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Status Update Error: " + e.getMessage());
        }
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public int getRestaurantId() { return restaurantId; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public double getTotalPrice() { return totalPrice; }
    public String getDeliveryAddress() { return deliveryAddress; }

    @Override
    public String toString() {
        return "Order #" + orderId + " - Status: " + status + " - Total: Rs. " + totalPrice;
    }
}
