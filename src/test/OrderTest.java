package test;

import models.Order;
import models.OrderItem;
import models.MenuItem;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testOrderTotalCalculation() {
        // Arrange
        Order order = new Order(1, 101, 201, "123 Main St");
        MenuItem pizza = new MenuItem(1, "Pizza", "Cheese", 12.50, "Food");
        MenuItem coke = new MenuItem(2, "Coke", "Cold", 2.50, "Beverage");
        
        OrderItem item1 = new OrderItem(1, 1, 1, 2, 12.50); // 2 pizzas = 25.0
        OrderItem item2 = new OrderItem(2, 1, 2, 1, 2.50);  // 1 coke = 2.5
        
        // Act
        order.addItem(item1);
        order.addItem(item2);
        
        // Assert
        assertEquals("Total should be correctly calculated", 27.50, order.getTotalPrice(), 0.01);
    }

    @Test
    public void testRemoveItemUpdatesTotal() {
        // Arrange
        Order order = new Order(2, 102, 201, "456 Oak St");
        OrderItem item1 = new OrderItem(1, 2, 1, 1, 10.00);
        order.addItem(item1);
        
        // Act
        order.removeItem(0);
        
        // Assert
        assertEquals("Total should be 0 after item removed", 0.0, order.getTotalPrice(), 0.01);
        assertEquals("Items list should be empty", 0, order.getItems().size());
    }
}
