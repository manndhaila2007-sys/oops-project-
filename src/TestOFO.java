import database.Database;
import handlers.*;
import models.*;
import java.util.*;

/**
 * Test Class - Demonstrates how to use the OFO System
 * This is a console-based demonstration of the system functionality
 */
public class TestOFO {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Online Food Ordering System - Test Demo");
        System.out.println("========================================\n");

        // Get database instance (Singleton)
        Database db = Database.getInstance();

        // Test 1: Display Users
        testUsers(db);

        // Test 2: Display Restaurants
        testRestaurants(db);

        // Test 3: Create and Manage Order
        testOrderManagement(db);

        // Test 4: Payment Processing
        testPaymentProcessing(db);

        // Test 5: Reviews and Ratings
        testReviews(db);

        System.out.println("\n========================================");
        System.out.println("Test Demo Completed!");
        System.out.println("========================================");
    }

    // Test 1: User Operations
    static void testUsers(Database db) {
        System.out.println("\n--- TEST 1: USER OPERATIONS ---\n");

        // Display existing users
        List<User> users = db.userService.getAllUsers();
        System.out.println("Existing Users: " + users.size());
        for (User user : users) {
            user.displayUserInfo();
            System.out.println();
        }

        // Test login
        System.out.println("\nTesting User Login:");
        User loggedInUser = db.userService.loginUser("raj@email.com", "password123");
        if (loggedInUser != null) {
            System.out.println("✓ Login successful for: " + loggedInUser.getName());
        } else {
            System.out.println("✗ Login failed");
        }
    }

    // Test 2: Restaurant Operations
    static void testRestaurants(Database db) {
        System.out.println("\n--- TEST 2: RESTAURANT OPERATIONS ---\n");

        // Display all restaurants
        List<Restaurant> restaurants = db.restaurantService.getAllRestaurants();
        System.out.println("Total Restaurants: " + restaurants.size() + "\n");
        
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
            
            // Display menu items
            List<MenuItem> menu = db.restaurantService.getRestaurantMenu(restaurant.getRestaurantId());
            System.out.println("  Menu Items:");
            for (MenuItem item : menu) {
                System.out.println("    - " + item);
            }
            System.out.println();
        }

        // Search restaurants
        System.out.println("\nSearching Restaurants by Cuisine:");
        List<Restaurant> indianRestaurants = db.restaurantService.searchRestaurantsByCuisine("Indian");
        System.out.println("Indian Restaurants Found: " + indianRestaurants.size());
        for (Restaurant r : indianRestaurants) {
            System.out.println("  - " + r.getRestaurantName());
        }
    }

    // Test 3: Order Management
    static void testOrderManagement(Database db) {
        System.out.println("\n--- TEST 3: ORDER MANAGEMENT ---\n");

        // Create an order
        System.out.println("Creating a new order...");
        int orderId = db.orderService.createOrder(1, 1, "123 Customer Street, Mumbai");
        System.out.println("✓ Order created with ID: " + orderId + "\n");

        // Add items to order
        System.out.println("Adding items to order...");
        db.orderService.addItemToOrder(orderId, 1, "Butter Chicken", 250, 2);
        db.orderService.addItemToOrder(orderId, 2, "Paneer Tikka", 180, 1);
        db.orderService.addItemToOrder(orderId, 3, "Biryani", 300, 1);
        System.out.println("✓ Items added to order\n");

        // Get order details
        Order order = db.orderService.getOrder(orderId);
        System.out.println("Order Details:");
        System.out.println("  Order ID: " + order.getOrderId());
        System.out.println("  Status: " + order.getStatus());
        System.out.println("  Total Price: Rs. " + order.getTotalPrice());
        System.out.println("  Delivery Address: " + order.getDeliveryAddress());
        
        System.out.println("\n  Items in Order:");
        for (OrderItem item : order.getItems()) {
            System.out.println("    - " + item);
        }

        // Update order status
        System.out.println("\nUpdating order status...");
        db.orderService.updateOrderStatus(orderId, Order.OrderStatus.CONFIRMED);
        System.out.println("✓ Order status updated to: " + 
                          db.orderService.getOrder(orderId).getStatus());
    }

    // Test 4: Payment Processing
    static void testPaymentProcessing(Database db) {
        System.out.println("\n--- TEST 4: PAYMENT PROCESSING ---\n");

        // Process card payment
        System.out.println("Processing Card Payment...");
        int paymentId = db.paymentService.addCardPayment("1234567890123456", "Raj Kumar", "12/25", 5000);
        boolean cardPaymentSuccess = db.paymentService.processPayment(paymentId, 780);
        System.out.println(cardPaymentSuccess ? "✓ Card Payment Successful" : "✗ Card Payment Failed");

        // Process wallet payment
        System.out.println("\nProcessing Wallet Payment...");
        int walletPaymentId = db.paymentService.addWalletPayment(2000);
        boolean walletPaymentSuccess = db.paymentService.processPayment(walletPaymentId, 600);
        System.out.println(walletPaymentSuccess ? "✓ Wallet Payment Successful" : "✗ Wallet Payment Failed");

        // Process COD payment
        System.out.println("\nProcessing Cash on Delivery...");
        int codPaymentId = db.paymentService.addCODPayment();
        boolean codPaymentSuccess = db.paymentService.processPayment(codPaymentId, 500);
        System.out.println(codPaymentSuccess ? "✓ COD Payment Successful" : "✗ COD Payment Failed");

        // Display payment types
        System.out.println("\nPayment Methods Used:");
        System.out.println("  - " + db.paymentService.getPaymentType(paymentId));
        System.out.println("  - " + db.paymentService.getPaymentType(walletPaymentId));
        System.out.println("  - " + db.paymentService.getPaymentType(codPaymentId));
    }

    // Test 5: Reviews and Ratings
    static void testReviews(Database db) {
        System.out.println("\n--- TEST 5: REVIEWS & RATINGS ---\n");

        // Add reviews
        System.out.println("Adding Reviews...");
        int review1 = db.reviewService.addReview(1, 1, 1, 4.5, "Great food and fast delivery!");
        int review2 = db.reviewService.addReview(1, 2, 1, 5.0, "Excellent quality, highly recommend!");
        int review3 = db.reviewService.addReview(2, 1, 2, 3.5, "Good taste, but packaging could be better");
        
        System.out.println("✓ Reviews added (IDs: " + review1 + ", " + review2 + ", " + review3 + ")\n");

        // Get restaurant average rating
        System.out.println("Restaurant Average Ratings:");
        List<Restaurant> restaurants = db.restaurantService.getAllRestaurants();
        for (Restaurant restaurant : restaurants) {
            double avgRating = db.reviewService.getAverageRestaurantRating(restaurant.getRestaurantId());
            System.out.println("  " + restaurant.getRestaurantName() + ": " + 
                             String.format("%.1f", avgRating) + "⭐");
        }

        // Display all reviews for a restaurant
        System.out.println("\nReviews for Spice Garden (Restaurant 1):");
        List<Review> reviews = db.reviewService.getRestaurantReviews(1);
        for (Review review : reviews) {
            System.out.println("  - " + review);
        }
    }
}
