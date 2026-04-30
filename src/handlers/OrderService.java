package handlers;

import models.*;
import java.util.*;

public class OrderService {
    private Map<Integer, Order> orders = new HashMap<>();
    private int orderCounter = 1;

    public int createOrder(int customerId, int restaurantId, String deliveryAddress) {
        Order order = new Order(orderCounter, customerId, restaurantId, deliveryAddress);
        orders.put(orderCounter, order);
        return orderCounter++;
    }

    public boolean addItemToOrder(int orderId, int itemId, String itemName, double price, int quantity) {
        Order order = orders.get(orderId);
        if (order != null) {
            OrderItem item = new OrderItem(itemId, itemName, price, quantity);
            order.addItem(item);
            return true;
        }
        return false;
    }

    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    public List<Order> getCustomerOrders(int customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getCustomerId() == customerId) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    public List<Order> getRestaurantOrders(int restaurantId) {
        List<Order> restaurantOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getRestaurantId() == restaurantId) {
                restaurantOrders.add(order);
            }
        }
        return restaurantOrders;
    }

    public boolean updateOrderStatus(int orderId, Order.OrderStatus newStatus) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.updateStatus(newStatus);
            return true;
        }
        return false;
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getStatus() == status) {
                result.add(order);
            }
        }
        return result;
    }

    public boolean cancelOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order != null && order.getStatus() != Order.OrderStatus.DELIVERED) {
            order.updateStatus(Order.OrderStatus.CANCELLED);
            return true;
        }
        return false;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public boolean removeItemFromOrder(int orderId, int itemIndex) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.removeItem(itemIndex);
            return true;
        }
        return false;
    }
}
