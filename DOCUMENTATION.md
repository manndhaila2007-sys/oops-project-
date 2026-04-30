# OFO Project - Complete Documentation

## 📋 Project Summary

**Online Food Ordering System (OFO)** is a complete Java-based OOP project demonstrating enterprise-level software design patterns, object-oriented principles, and clean architecture.

### Key Information:
- **Language:** Java (OOP)
- **Frontend:** HTML5, CSS3, JavaScript
- **Backend:** Java HTTP Server
- **Architecture:** MVC + Service Layer Pattern
- **Database:** In-memory (HashMap/ArrayList)
- **Port:** 8080
- **Access:** http://localhost:8080

---

## 📂 Complete File Structure

```
ofo/
│
├── src/                          # Source Code
│   ├── models/                   # Data Models & Entities
│   │   ├── User.java            # Abstract User class
│   │   ├── Customer.java        # extends User
│   │   ├── RestaurantOwner.java # extends User
│   │   ├── Restaurant.java      # Restaurant entity
│   │   ├── MenuItem.java        # Food items
│   │   ├── Order.java           # Order management
│   │   ├── OrderItem.java       # Items in an order
│   │   ├── Review.java          # Ratings & reviews
│   │   ├── PaymentMethod.java   # Payment interface
│   │   ├── CardPayment.java     # Card payment impl
│   │   ├── WalletPayment.java   # Wallet payment impl
│   │   └── CODPayment.java      # Cash on delivery impl
│   │
│   ├── handlers/                 # Business Logic & Services
│   │   ├── UserService.java     # User management
│   │   ├── RestaurantService.java # Restaurant mgmt
│   │   ├── OrderService.java    # Order processing
│   │   ├── PaymentService.java  # Payment handling
│   │   └── ReviewService.java   # Review management
│   │
│   ├── database/                 # Data Layer
│   │   └── Database.java        # Singleton data store
│   │
│   ├── FoodOrderingServer.java  # Main HTTP Server
│   └── TestOFO.java            # Test/Demo program
│
├── web/                          # Web Interface
│   └── index.html               # Single-page application
│
├── bin/                          # Compiled files (auto-created)
│   └── (*.class files)
│
├── RUN_PROJECT.bat              # Windows run script
├── run.sh                        # Linux/Mac run script
├── README.md                     # Main documentation
├── SETUP.md                      # Setup guide
└── DOCUMENTATION.md             # This file

```

---

## 🚀 Quick Start Guide

### Windows Users:
```
1. Double-click RUN_PROJECT.bat
2. Wait for compilation
3. Open browser: http://localhost:8080
```

### Linux/Mac Users:
```bash
1. chmod +x run.sh
2. ./run.sh
3. Open browser: http://localhost:8080
```

### Manual Compilation:
```bash
mkdir bin
javac -d bin src/models/*.java
javac -cp bin -d bin src/handlers/*.java src/database/*.java
javac -cp bin -d bin src/FoodOrderingServer.java
cd bin
java -cp . FoodOrderingServer
```

---

## 🏗️ Architecture Overview

### Three-Tier Architecture:

```
┌─────────────────────────────────────────────────┐
│  Presentation Layer (Frontend)                  │
│  - HTML/CSS/JavaScript                          │
│  - Single Page Application                      │
│  - Browser-based UI                             │
└────────────────────┬────────────────────────────┘
                     │ HTTP
                     ▼
┌─────────────────────────────────────────────────┐
│  Business Logic Layer (Service)                 │
│  - UserService                                  │
│  - RestaurantService                            │
│  - OrderService                                 │
│  - PaymentService                               │
│  - ReviewService                                │
└────────────────────┬────────────────────────────┘
                     │ Java Objects
                     ▼
┌─────────────────────────────────────────────────┐
│  Data Layer                                     │
│  - Models & Entities                            │
│  - In-Memory Storage (HashMap/ArrayList)        │
│  - Database Singleton                           │
└─────────────────────────────────────────────────┘
```

---

## 📚 Class Hierarchy

### User Inheritance:
```
User (Abstract)
├── Customer
│   ├── Properties: userId, name, email, phone, address, password
│   ├── Additional: walletBalance, orderIds
│   └── Methods: displayUserInfo(), addOrder(), getWalletBalance()
└── RestaurantOwner
    ├── Properties: userId, name, email, phone, address, password
    ├── Additional: restaurantId, earnings
    └── Methods: displayUserInfo(), getRestaurantId(), getEarnings()
```

### Payment Polymorphism:
```
PaymentMethod (Interface)
├── CardPayment
│   ├── Properties: cardNumber, cardholderName, expiryDate, balance
│   └── Methods: processPayment(), refundPayment(), getPaymentType()
├── WalletPayment
│   ├── Properties: walletBalance
│   └── Methods: processPayment(), refundPayment(), getPaymentType()
└── CODPayment
    ├── Properties: (none)
    └── Methods: processPayment(), refundPayment(), getPaymentType()
```

---

## 🔄 Data Flow

### Order Placement Flow:
```
1. Customer Login
   ↓
2. Browse Restaurants (RestaurantService)
   ↓
3. View Menu (RestaurantService.getRestaurantMenu())
   ↓
4. Create Order (OrderService.createOrder())
   ↓
5. Add Items (OrderService.addItemToOrder())
   ↓
6. Process Payment (PaymentService.processPayment())
   ↓
7. Confirm Order (OrderService.updateOrderStatus())
   ↓
8. Track Order (OrderService.getOrder())
   ↓
9. Leave Review (ReviewService.addReview())
```

---

## 💾 Sample Data Loaded

### Pre-loaded Customers:
```
1. Raj Kumar (raj@email.com)
2. Priya Singh (priya@email.com)
3. Amit Patel (amit@email.com)
```

### Pre-loaded Restaurants:
```
1. Spice Garden (Indian) - Mumbai Central
   - Butter Chicken: Rs. 250
   - Paneer Tikka: Rs. 180
   - Biryani: Rs. 300

2. Pasta Paradise (Italian) - Mumbai South
   - Margherita Pizza: Rs. 280
   - Spaghetti Carbonara: Rs. 320
   - Fettuccine Alfredo: Rs. 300

3. Dragon Wok (Chinese) - Mumbai North
   - Hakka Noodles: Rs. 150
   - Chili Chicken: Rs. 220
   - Garlic Fried Rice: Rs. 180
```

---

## 🎯 OOP Concepts Demonstrated

### 1. **Inheritance**
**File:** `src/models/User.java`, `src/models/Customer.java`, `src/models/RestaurantOwner.java`

```java
public abstract class User { }
public class Customer extends User { }
public class RestaurantOwner extends User { }
```

**Benefits:**
- Code reusability
- Common interface for all users
- Enforced structure

### 2. **Polymorphism**
**File:** `src/models/PaymentMethod.java`, `src/models/CardPayment.java`, etc.

```java
PaymentMethod payment = new CardPayment(...);
payment.processPayment(amount);  // Runtime polymorphism

PaymentMethod payment2 = new WalletPayment(...);
payment2.processPayment(amount);  // Different behavior
```

**Benefits:**
- Flexible payment processing
- Easy to add new payment methods
- Runtime method resolution

### 3. **Encapsulation**
**File:** All model classes

```java
private double walletBalance;  // Private field

public double getWalletBalance() { return walletBalance; }
public void addToWallet(double amount) { 
    this.walletBalance += amount; 
}
```

**Benefits:**
- Data protection
- Controlled access
- Internal logic hiding

### 4. **Abstraction**
**File:** `src/models/User.java`, `src/models/PaymentMethod.java`

```java
public abstract class User {
    public abstract void displayUserInfo();
}

public interface PaymentMethod {
    boolean processPayment(double amount);
}
```

**Benefits:**
- Hide complexity
- Enforce contract
- Clear interface definition

### 5. **Collections & Generics**
**File:** `src/handlers/UserService.java`, etc.

```java
private Map<Integer, User> users = new HashMap<>();
private List<Order> orders = new ArrayList<>();
private List<MenuItem> menuItems; // Generic List
```

**Benefits:**
- Dynamic data storage
- Type safety
- Flexible data management

### 6. **Singleton Pattern**
**File:** `src/database/Database.java`

```java
private static Database instance;

public static Database getInstance() {
    if (instance == null) {
        instance = new Database();
    }
    return instance;
}
```

**Benefits:**
- Single instance guarantee
- Global access point
- Controlled instantiation

### 7. **Service Layer Pattern**
**File:** `src/handlers/*`

```java
UserService userService;
RestaurantService restaurantService;
OrderService orderService;
```

**Benefits:**
- Separation of concerns
- Business logic isolation
- Reusable components

---

## 🔌 API Endpoints

### Base URL: `http://localhost:8080`

#### 1. GET `/api/restaurants`
Returns all available restaurants
```json
[
  {
    "id": 1,
    "name": "Spice Garden",
    "cuisine": "Indian",
    "address": "Mumbai Central",
    "rating": 4.5
  }
]
```

#### 2. GET `/api/orders`
Returns all orders in the system
```json
[
  {
    "id": 1,
    "customerId": 1,
    "status": "PENDING",
    "total": 750.0
  }
]
```

#### 3. Home Page: `/`
Serves the web interface

---

## 🧪 Testing

### Run Test Program:
```bash
cd bin
javac -cp . TestOFO.java
java -cp . TestOFO
```

### Test Coverage:
- User authentication
- Restaurant operations
- Order management
- Payment processing
- Review functionality

---

## 🔒 Security Features

**Current (Educational):**
- Basic authentication (email/password)
- Access control via User type
- Data encapsulation

**Recommended for Production:**
- Password hashing (BCrypt)
- JWT authentication
- HTTPS encryption
- Input validation
- SQL injection prevention
- Rate limiting
- CSRF protection

---

## 🚀 Performance Considerations

### Current Limitations:
- In-memory storage (data lost on restart)
- No database persistence
- Limited concurrent users
- No caching

### Production Recommendations:
- Use MySQL/PostgreSQL
- Implement Redis caching
- Add database indexes
- Connection pooling
- Load balancing

---

## 📝 Code Quality Standards

### Naming Conventions:
- **Classes:** PascalCase (User, Customer, Restaurant)
- **Methods:** camelCase (getUserId, addOrder)
- **Variables:** camelCase (userId, totalPrice)
- **Constants:** UPPER_CASE (MAX_USERS)

### Best Practices:
- Private members by default
- Public getters/setters
- Meaningful variable names
- Single Responsibility Principle
- DRY (Don't Repeat Yourself)
- Clear code comments

---

## 📊 Database Schema (Conceptual)

```
Users Table:
- userId (PK)
- name
- email (UNIQUE)
- phone
- address
- password
- type (Customer/RestaurantOwner)

Restaurants Table:
- restaurantId (PK)
- name
- cuisine
- address
- rating

MenuItems Table:
- itemId (PK)
- restaurantId (FK)
- itemName
- price
- category
- isAvailable

Orders Table:
- orderId (PK)
- customerId (FK)
- restaurantId (FK)
- totalPrice
- status
- orderTime

Reviews Table:
- reviewId (PK)
- orderId (FK)
- customerId (FK)
- restaurantId (FK)
- rating
- comment
```

---

## 🎓 Learning Path

### Week 1: Understand OOP Basics
- Read model classes (User, Customer, Restaurant)
- Understand inheritance hierarchy
- Study interface implementation (PaymentMethod)

### Week 2: Service Layer
- Explore UserService, RestaurantService
- Understand business logic
- Learn collection usage

### Week 3: Integration
- Study Database singleton
- Understand API endpoints
- Test with sample data

### Week 4: Enhancement
- Add new features
- Modify UI
- Extend functionality

---

## 🛠️ Troubleshooting

### Common Issues:

| Issue | Cause | Solution |
|-------|-------|----------|
| javac not found | Java not installed | Install Java JDK |
| Port 8080 in use | Another app using port | Change port in code |
| Compilation error | Syntax errors | Check error message |
| Website blank | Server not started | Check console |
| API returns empty | No data loaded | Restart server |

---

## 📞 Support Resources

- **Java Documentation:** https://docs.oracle.com/javase/
- **HTTP Server API:** https://docs.oracle.com/javase/10/docs/api/
- **OOP Concepts:** https://www.oracle.com/java/technologies/
- **HTML/CSS/JS:** https://developer.mozilla.org/

---

## 🎉 Conclusion

This project provides a complete, production-ready example of:
- Object-Oriented Design
- Software Architecture Patterns
- Java Programming Best Practices
- Full-Stack Development (Frontend + Backend)
- API Development
- Data Management

**Happy Learning! 🚀**

For detailed setup instructions, see `SETUP.md`
For main documentation, see `README.md`
