package test;

import models.CardPayment;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void testSuccessfulCardPayment() {
        // Arrange
        CardPayment card = new CardPayment("1234567890123456", "John Doe", "12/25", 100.0);
        
        // Act
        boolean result = card.processPayment(45.0);
        
        // Assert
        assertTrue("Payment should be successful", result);
        assertEquals("Balance should be deducted", 55.0, card.getBalance(), 0.01);
    }

    @Test
    public void testInsufficientFundsPayment() {
        // Arrange
        CardPayment card = new CardPayment("1234567890123456", "Jane Doe", "11/24", 20.0);
        
        // Act
        boolean result = card.processPayment(50.0);
        
        // Assert
        assertFalse("Payment should fail due to insufficient funds", result);
        assertEquals("Balance should remain unchanged", 20.0, card.getBalance(), 0.01);
    }

    @Test
    public void testInvalidCardPayment() {
        // Arrange (Invalid card number length)
        CardPayment card = new CardPayment("1234", "Invalid User", "01/30", 500.0);
        
        // Act
        boolean result = card.processPayment(10.0);
        
        // Assert
        assertFalse("Payment should fail due to invalid card", result);
    }
}
