# Online Food Ordering System (OFO)
## Java OOP Project with Advanced Features

A complete online food ordering system implemented in Java, demonstrating core Object-Oriented Programming (OOP) concepts, design patterns, and clean architecture.

---

## 🚀 Quick Start

### Prerequisites
- Java JDK 8 or higher
- Windows (for batch file) or command line terminal

### Running the Project

**Option 1: Using Batch File (Windows)**
```bash
Double-click: RUN_PROJECT.bat
```

**Option 2: Manual Compilation & Execution**
```bash
# Create bin directory
mkdir bin

# Compile all Java files
javac -d bin src/models/*.java
javac -cp bin -d bin src/handlers/*.java src/database/*.java
javac -cp bin -d bin src/FoodOrderingServer.java

# Run the server
cd bin
java -cp . FoodOrderingServer
```

### Access the Website
Once the server starts, open your browser and navigate to:
```
http://localhost:8080
```

---

## 📁 Project Structure

```
ofo/
├── src/
│   ├── models/              # Data Models & Entities
│   │   ├── User.java              (Abstract Class)
│   │   ├── Customer.java          (extends User)
│   │   ├── RestaurantOwner.java   (extends User)
│   │   ├── Restaurant.java
│   │   ├── MenuItem.java
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   ├── Review.java
│   │   ├── PaymentMethod.java     (Interface)
│   │   ├── CardPayment.java       (implements PaymentMethod)
│   │   ├── WalletPayment.java     (implements PaymentMethod)
│   │   └── CODPayment.java        (implements PaymentMethod)
│   │
│   ├── handlers/            # Business Logic & Services
│   │   ├── UserService.java
│   │   ├── RestaurantService.java
│   │   ├── OrderService.java
│   │   ├── PaymentService.java
│   │   └── ReviewService.java
│   │
│   ├── database/            # Database Layer
│   │   └── Database.java          (Singleton Pattern)
│   │
│   └── FoodOrderingServer.java     # Main Server (HTTP Server)
│
├── web/
│   └── index.html           # Frontend Web Interface
│
├── RUN_PROJECT.bat          # Run Script (Windows)
└── README.md               # This file
```

---

## 🎯 Features

### User Features
- ✅ User Registration & Login
- ✅ Profile Management
- ✅ Browse Restaurants by Cuisine/Name
- ✅ View Restaurant Menus
- ✅ Place Orders
- ✅ Track Order Status
- ✅ Rate & Review Restaurants
- ✅ Multiple Payment Methods

### Restaurant Features
- ✅ Add/Manage Restaurants
- ✅ Add/Update Menu Items
- ✅ Manage Orders
- ✅ View Earnings
- ✅ Monitor Restaurant Rating

### Payment Features
- ✅ Card Payment
- ✅ Wallet Payment
- ✅ Cash on Delivery (COD)
- ✅ Payment Processing & Refunds

---

## 🏗️ OOP Concepts Demonstrated

### 1. **Inheritance**
- `User` (Abstract) → `Customer`, `RestaurantOwner` (Subclasses)
- `PaymentMethod` (Interface) → Multiple implementations
```java
public abstract class User {
    protected int userId;
    protected String name;
    // ...
    public abstract void displayUserInfo();
}

public class Customer extends User {
    @Override
    public void displayUserInfo() {
        // Customer-specific implementation
    }
}
```

### 2. **Polymorphism**
- Method Overriding: `displayUserInfo()` in different user types
- Interface Implementation: Multiple payment methods with same interface
```java
PaymentMethod payment = new CardPayment(...);
payment.processPayment(amount);  // Polymorphic behavior
```

### 3. **Encapsulation**
- Private member variables with public getters/setters
- Data hiding and controlled access
```java
private double walletBalance;

public double getWalletBalance() { 
    return walletBalance; 
}

public void addToWallet(double amount) { 
    this.walletBalance += amount; 
}
```

### 4. **Abstraction**
- Abstract `User` class defining common interface
- `PaymentMethod` interface abstracting payment logic
```java
public interface PaymentMethod {
    boolean processPayment(double amount);
    void refundPayment(double amount);
    String getPaymentType();
}
```

### 5. **Collections & Generics**
- HashMap for ID-based object storage
- ArrayList for dynamic collections
- Generic List interface
```java
private Map<Integer, User> users = new HashMap<>();
private List<Order> orders = new ArrayList<>();
private List<MenuItem> menuItems; // Generic List
```

### 6. **Singleton Pattern**
- Database class using Singleton for single instance
```java
public static Database getInstance() {
    if (instance == null) {
        instance = new Database();
    }
    return instance;
}
```

### 7. **Service Layer Pattern**
- UserService, RestaurantService, OrderService
- Separation of concerns
- Reusable business logic

---

## 📡 API Endpoints

### Restaurants
- `GET /api/restaurants` - Get all restaurants

### Orders
- `GET /api/orders` - Get all orders

### Users
- `GET /api/users` - User management endpoints

### Reviews
- `GET /api/reviews` - Review management endpoints

---

## 🔧 Class Details

### Models
| Class | Purpose |
|-------|---------|
| User | Abstract base class for all users |
| Customer | Customer with wallet and orders |
| RestaurantOwner | Restaurant owner with earnings |
| Restaurant | Restaurant details and menu |
| MenuItem | Food items with price and availability |
| Order | Customer order with items and status |
| OrderItem | Individual item in an order |
| Review | Customer review with rating |
| PaymentMethod | Interface for payment processing |
| CardPayment | Credit/Debit card payment |
| WalletPayment | Digital wallet payment |
| CODPayment | Cash on Delivery payment |

### Services
| Service | Responsibility |
|---------|-----------------|
| UserService | User registration, login, profile |
| RestaurantService | Restaurant & menu management |
| OrderService | Order creation and management |
| PaymentService | Payment method management |
| ReviewService | Review and rating management |

---

## 💾 Sample Data

The system comes pre-loaded with sample data:

### Sample Restaurants:
1. **Spice Garden** (Indian) - Mumbai Central
   - Butter Chicken - Rs. 250
   - Paneer Tikka - Rs. 180
   - Biryani - Rs. 300

2. **Pasta Paradise** (Italian) - Mumbai South
   - Margherita Pizza - Rs. 280
   - Spaghetti Carbonara - Rs. 320
   - Fettuccine Alfredo - Rs. 300

3. **Dragon Wok** (Chinese) - Mumbai North
   - Hakka Noodles - Rs. 150
   - Chili Chicken - Rs. 220
   - Garlic Fried Rice - Rs. 180

### Sample Customers:
- Raj Kumar (raj@email.com)
- Priya Singh (priya@email.com)
- Amit Patel (amit@email.com)

---

## 🎓 Learning Outcomes

This project demonstrates:
- ✅ Object-Oriented Programming principles
- ✅ Software design patterns
- ✅ Clean code architecture
- ✅ HTTP server implementation
- ✅ Frontend-backend integration
- ✅ API development
- ✅ Data management with collections
- ✅ User interface design

---

## 🐛 Troubleshooting

### Issue: "javac is not recognized"
**Solution:** Add Java to system PATH or use full path to javac

### Issue: Port 8080 already in use
**Solution:** Change the PORT in FoodOrderingServer.java (line ~18)

### Issue: Cannot compile Java files
**Solution:** Ensure you're in the correct directory and Java is properly installed

---

## 🚀 Future Enhancements

- [ ] Database integration (MySQL/PostgreSQL)
- [ ] User authentication with JWT
- [ ] Real-time order tracking with WebSocket
- [ ] Email notifications
- [ ] Admin dashboard
- [ ] Analytics and reporting
- [ ] Mobile app
- [ ] Coupon/Promo system
- [ ] Delivery tracking with GPS
- [ ] Advanced search and filters

---

## 📝 Code Style & Conventions

- **Naming:** camelCase for variables/methods, PascalCase for classes
- **Access Modifiers:** Private by default, public when necessary
- **Documentation:** Javadoc for public methods
- **Constants:** UPPER_CASE for constants
- **Comments:** Meaningful comments for complex logic

---

## 🤝 Contributing

To extend this project:

1. Add new models in `src/models/`
2. Add business logic in `src/handlers/`
3. Add new API endpoints in `FoodOrderingServer.java`
4. Update the web interface in `web/index.html`

---

## 📄 License

This project is created for educational purposes.

---

## 👨‍💻 Author

Created as a comprehensive Java OOP project demonstrating best practices and design patterns.

---

## 📞 Support

For issues or questions, review the code comments and the OOP concepts section in this README.

---

**Happy Coding! 🎉**

Server running at: **http://localhost:8080**
