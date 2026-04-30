package test;

import models.MenuItem;
import org.junit.Test;
import static org.junit.Assert.*;

public class MenuItemTest {

    @Test
    public void testMenuItemCreation() {
        // Arrange & Act
        MenuItem item = new MenuItem(10, "Burger", "Juicy beef burger", 8.99, "Fast Food");
        
        // Assert
        assertEquals("Burger", item.getName());
        assertEquals("Juicy beef burger", item.getDescription());
        assertEquals(8.99, item.getPrice(), 0.01);
        assertEquals("Fast Food", item.getCategory());
    }
}
