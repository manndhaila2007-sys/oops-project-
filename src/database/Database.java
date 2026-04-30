package database;

import handlers.*;
import models.*;

public class Database {
    private static Database instance;
    public UserService userService;
    public RestaurantService restaurantService;
    public OrderService orderService;
    public PaymentService paymentService;
    public ReviewService reviewService;

    private Database() {
        userService = new UserService();
        restaurantService = new RestaurantService();
        orderService = new OrderService();
        paymentService = new PaymentService();
        reviewService = new ReviewService();
        initializeSampleData();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void initializeSampleData() {
        // Add sample customers
        userService.registerCustomer("Raj Kumar", "raj@email.com", "9876543210", "Mumbai", "password123");
        userService.registerCustomer("Priya Singh", "priya@email.com", "9123456789", "Delhi", "password456");
        userService.registerCustomer("Amit Patel", "amit@email.com", "8765432109", "Bangalore", "password789");

        // Add sample restaurants
        int rest1 = restaurantService.addRestaurant("Spice Garden", "Indian", "Mumbai Central");
        int rest2 = restaurantService.addRestaurant("Pasta Paradise", "Italian", "Mumbai South");
        int rest3 = restaurantService.addRestaurant("Dragon Wok", "Chinese", "Mumbai North");

        // Add menu items to restaurants
        restaurantService.addMenuItemToRestaurant(rest1, "Butter Chicken", "Creamy butter chicken curry", 250, "Non-Veg");
        restaurantService.addMenuItemToRestaurant(rest1, "Paneer Tikka", "Grilled paneer with spices", 180, "Veg");
        restaurantService.addMenuItemToRestaurant(rest1, "Biryani", "Fragrant basmati rice biryani", 300, "Non-Veg");

        restaurantService.addMenuItemToRestaurant(rest2, "Margherita Pizza", "Classic tomato and cheese", 280, "Veg");
        restaurantService.addMenuItemToRestaurant(rest2, "Spaghetti Carbonara", "Creamy pasta with bacon", 320, "Non-Veg");
        restaurantService.addMenuItemToRestaurant(rest2, "Fettuccine Alfredo", "Rich cream sauce pasta", 300, "Veg");

        restaurantService.addMenuItemToRestaurant(rest3, "Hakka Noodles", "Tossed noodles with vegetables", 150, "Veg");
        restaurantService.addMenuItemToRestaurant(rest3, "Chili Chicken", "Spicy chicken with chilies", 220, "Non-Veg");
        restaurantService.addMenuItemToRestaurant(rest3, "Garlic Fried Rice", "Fragrant rice with garlic", 180, "Veg");

        // Add payment methods
        paymentService.addCardPayment("1234567890123456", "Raj Kumar", "12/25", 10000);
        paymentService.addWalletPayment(5000);
        paymentService.addCODPayment();

        System.out.println("Database initialized with sample data!");
    }
}
