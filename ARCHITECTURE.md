# OFO System - Visual Guide & Flow Diagrams

## 🏗️ System Architecture

```
┌──────────────────────────────────────────────────────────┐
│                   WEB BROWSER (Client)                   │
│              http://localhost:8080                       │
│  ┌────────────────────────────────────────────────────┐  │
│  │    index.html (Single Page Application)            │  │
│  │  - Navigation (Home, Restaurants, Orders, About)   │  │
│  │  - Restaurant Grid & Search                        │  │
│  │  - Order Tracking                                  │  │
│  │  - Review System                                   │  │
│  └────────────────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────┘
                    ▲ HTTP Requests/Responses
                    │ JSON Data Exchange
                    ▼
┌──────────────────────────────────────────────────────────┐
│          JAVA HTTP SERVER (Backend)                      │
│     FoodOrderingServer.java (Port 8080)                 │
│  ┌────────────────────────────────────────────────────┐  │
│  │    HTTP Handlers (Endpoints)                       │  │
│  │  - HomeHandler ("/")                               │  │
│  │  - RestaurantHandler ("/api/restaurants")          │  │
│  │  - OrderHandler ("/api/orders")                    │  │
│  │  - UserHandler ("/api/users")                      │  │
│  │  - ReviewHandler ("/api/reviews")                  │  │
│  └────────────────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────┘
                    ▲ Business Logic Calls
                    │
                    ▼
┌──────────────────────────────────────────────────────────┐
│        SERVICE LAYER (Business Logic)                    │
│  ┌─────────────┐ ┌──────────────────┐                   │
│  │ UserService │ │RestaurantService │                   │
│  └─────────────┘ └──────────────────┘                   │
│  ┌─────────────┐ ┌──────────────────┐                   │
│  │OrderService │ │ PaymentService   │                   │
│  └─────────────┘ └──────────────────┘                   │
│  ┌──────────────┐                                        │
│  │ReviewService │                                        │
│  └──────────────┘                                        │
└──────────────────────────────────────────────────────────┘
                    ▲ Object Operations
                    │
                    ▼
┌──────────────────────────────────────────────────────────┐
│           MODEL LAYER (Data Objects)                     │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐              │
│  │User      │  │Restaurant│  │MenuItem  │              │
│  │├─Customer│  │└─menu[]  │  │├─name    │              │
│  │└─Owner   │  │└─rating  │  │├─price   │              │
│  └──────────┘  └──────────┘  │└─category│              │
│  ┌──────────┐  ┌──────────┐  └──────────┘              │
│  │Order     │  │Payment   │  ┌──────────┐              │
│  │├─items[] │  │├─Card    │  │Review    │              │
│  │├─status  │  │├─Wallet  │  │├─rating  │              │
│  │└─total   │  │└─COD     │  │└─comment │              │
│  └──────────┘  └──────────┘  └──────────┘              │
└──────────────────────────────────────────────────────────┘
                    ▲ CRUD Operations
                    │
                    ▼
┌──────────────────────────────────────────────────────────┐
│         DATABASE LAYER (Singleton)                       │
│    Database.java (In-Memory Storage)                     │
│  ┌────────────────────────────────────────────────────┐  │
│  │  Map<Integer, User> users                          │  │
│  │  Map<Integer, Restaurant> restaurants              │  │
│  │  Map<Integer, Order> orders                        │  │
│  │  Map<Integer, PaymentMethod> payments              │  │
│  │  Map<Integer, Review> reviews                      │  │
│  └────────────────────────────────────────────────────┘  │
└──────────────────────────────────────────────────────────┘
```

---

## 👥 User Hierarchy

```
                    User (Abstract)
                 ┌──────┴──────┐
                 │             │
            Customer      RestaurantOwner
            ├─userId        ├─userId
            ├─name          ├─name
            ├─email         ├─email
            ├─phone         ├─phone
            ├─address       ├─address
            ├─password      ├─password
            ├─wallet        ├─restaurantId
            ├─orderIds[]    └─earnings
            └─displayUserInfo()
                            │
                        RestaurantOwner
                        └─displayUserInfo()
```

---

## 💳 Payment Method Polymorphism

```
              PaymentMethod (Interface)
             ┌────────┴────────┬────────────┐
             │                 │            │
        CardPayment      WalletPayment    CODPayment
        ├─cardNumber    ├─balance       ├─(no fields)
        ├─balance       │               │
        │               │               │
        ├─processPayment() processPayment() processPayment()
        ├─refundPayment()  refundPayment()  refundPayment()
        └─getPaymentType() getPaymentType() getPaymentType()

Usage:
PaymentMethod payment = new CardPayment(...)
payment.processPayment(amount)  // Polymorphic call
```

---

## 🔄 Order Placement Flow

```
┌────────┐
│ Start  │
└────┬───┘
     │
     ▼
┌───────────────────┐
│ 1. User Login     │
│ UserService       │
│ .loginUser()      │
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 2. Browse         │
│ Restaurants       │
│ RestaurantService │
│ .getAllRestaurants│
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 3. View Menu      │
│ RestaurantService │
│ .getRestaurantMenu│
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 4. Create Order   │
│ OrderService      │
│ .createOrder()    │
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 5. Add Items      │
│ OrderService      │
│ .addItemToOrder() │
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 6. Process Payment│
│ PaymentService    │
│ .processPayment() │
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 7. Confirm Order  │
│ OrderService      │
│ .updateOrderStatus│
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 8. Track Order    │
│ OrderService      │
│ .getOrder()       │
└────┬──────────────┘
     │
     ▼
┌───────────────────┐
│ 9. Leave Review   │
│ ReviewService     │
│ .addReview()      │
└────┬──────────────┘
     │
     ▼
┌──────────┐
│ Complete │
└──────────┘
```

---

## 📦 Data Model Relationships

```
┌─────────────────┐
│   Restaurant    │ 1
│ ├─id            │──────┐
│ ├─name          │      │ has many
│ ├─cuisine       │      │
│ ├─rating        │      │
│ └─address       │      │
└─────────────────┘      │
                         │
                         ▼
                    ┌────────────┐
                    │  MenuItem  │ *
                    │ ├─itemId   │
                    │ ├─name     │
                    │ ├─price    │
                    │ └─category │
                    └────────────┘


┌──────────────────┐
│    Customer      │ 1
│ ├─userId         │──────┐
│ ├─name           │      │ places
│ ├─email          │      │
│ └─wallet         │      │
└──────────────────┘      │
                          ▼
                      ┌────────┐
                      │ Order  │ *
                      │ ├─id   │──────┐
                      │ ├─items│      │ contains
                      │ ├─total│      │
                      │ └─status      │
                      └────────┘      │
                                      ▼
                                  ┌──────────┐
                                  │OrderItem │ *
                                  │ ├─itemId │
                                  │ ├─name   │
                                  │ ├─price  │
                                  │ └─qty    │
                                  └──────────┘
```

---

## 🔀 Service Dependencies

```
FoodOrderingServer
├── RestaurantHandler
│   └── RestaurantService
│       └── Restaurant & MenuItem Objects
│
├── OrderHandler
│   └── OrderService
│       ├── Order & OrderItem Objects
│       └── uses ReviewService for ratings
│
├── UserHandler
│   └── UserService
│       ├── User & Customer Objects
│       └── RestaurantOwner Objects
│
├── PaymentHandler
│   └── PaymentService
│       └── PaymentMethod Implementations
│
└── ReviewHandler
    └── ReviewService
        ├── Review Objects
        ├── uses OrderService to validate orders
        └── updates Restaurant ratings
```

---

## 📊 Sample Data Structure

```
Database Instance (Singleton)
│
├─ Users
│  ├─ ID: 1 → Customer: Raj Kumar
│  ├─ ID: 2 → Customer: Priya Singh
│  └─ ID: 3 → Customer: Amit Patel
│
├─ Restaurants
│  ├─ ID: 1 → Spice Garden
│  │  └─ MenuItems: Butter Chicken, Paneer Tikka, Biryani
│  ├─ ID: 2 → Pasta Paradise
│  │  └─ MenuItems: Margherita Pizza, Spaghetti, Fettuccine
│  └─ ID: 3 → Dragon Wok
│     └─ MenuItems: Hakka Noodles, Chili Chicken, Garlic Rice
│
├─ Orders
│  ├─ ID: 1 → Customer 1, Restaurant 1, Items[], Status: PENDING
│  └─ ...more orders
│
├─ Payments
│  ├─ ID: 1 → CardPayment
│  ├─ ID: 2 → WalletPayment
│  └─ ID: 3 → CODPayment
│
└─ Reviews
   └─ ID: 1,2,3 → Customer ratings for restaurants
```

---

## 🎯 API Response Examples

### GET /api/restaurants
```json
[
  {
    "id": 1,
    "name": "Spice Garden",
    "cuisine": "Indian",
    "address": "Mumbai Central",
    "rating": 4.5
  },
  {
    "id": 2,
    "name": "Pasta Paradise",
    "cuisine": "Italian",
    "address": "Mumbai South",
    "rating": 4.7
  }
]
```

### GET /api/orders
```json
[
  {
    "id": 1,
    "customerId": 1,
    "status": "CONFIRMED",
    "total": 730.0
  },
  {
    "id": 2,
    "customerId": 2,
    "status": "PREPARING",
    "total": 580.0
  }
]
```

---

## 🏃 Execution Flow

```
User Launches RUN_PROJECT.bat
│
├─ Compile src/models/*.java ──────────────────────────┐
│  │                                                   │
│  ├─ User.java (Abstract)                             │
│  ├─ Customer.java (extends User)                     │
│  ├─ Restaurant.java                                  │
│  ├─ MenuItem.java                                    │
│  ├─ Order.java                                       │
│  ├─ PaymentMethod.java (Interface)                   │
│  └─ ...other model classes                           │
│                                                      │
├─ Compile src/handlers/*.java ─────────────────────┐  │
│  │                                                │  │
│  ├─ UserService.java                             │  │
│  ├─ RestaurantService.java                       │  │
│  ├─ OrderService.java                            │  │
│  ├─ PaymentService.java                          │  │
│  └─ ReviewService.java                           │  │
│                                                   │  │
├─ Compile src/database/Database.java ────────────┐│  │
│  │                                               ││  │
│  └─ Creates Singleton instance ─────────────────┘│  │
│     (loads sample data)                          │  │
│                                                  │  │
├─ Compile FoodOrderingServer.java ──────────────┘│  │
│  │                                               │  │
│  ├─ Creates HttpServer on port 8080             │  │
│  ├─ Creates HTTP handlers                       │  │
│  ├─ Registers endpoints                         │  │
│  └─ Starts listening                            │  │
│                                                  │  │
├─ "Server running at: http://localhost:8080"     │  │
│                                                  │  │
└─ Browser opens ──────────────────────────────────┘  │
   │                                                  │
   ├─ GET / ───────────────────► index.html        │  │
   │                                                  │
   ├─ JavaScript fetch('/api/restaurants')          │  │
   │   ────────────► RestaurantHandler              │  │
   │                ────────────► RestaurantService  │  │
   │                ────────────► JSON response      │  │
   │                ◄───────────── displayed in UI   │  │
   │                                                  │
   └─ Website functional! 🎉                       │  │
```

---

## 🔑 Key Design Patterns Used

### 1. Singleton Pattern (Database)
```java
private static Database instance;

public static Database getInstance() {
    if (instance == null) {
        instance = new Database();
    }
    return instance;
}
```

### 2. Service Layer Pattern
```
UI ↔ HTTP Handlers ↔ Services ↔ Models ↔ Database
```

### 3. Repository Pattern (Service Classes)
```java
public class UserService {
    private Map<Integer, User> users = new HashMap<>();
    // CRUD operations
}
```

### 4. MVC Architecture
```
Model  (User, Restaurant, Order...)
View   (index.html with JavaScript)
Controller (HTTP Handlers/Services)
```

---

## 🎓 Code Flow Example

### User Browsing Restaurants:

```
1. Browser loads http://localhost:8080
   ↓
2. FoodOrderingServer.HomeHandler processes request
   ↓
3. Returns index.html with JavaScript
   ↓
4. JavaScript: fetch('/api/restaurants')
   ↓
5. FoodOrderingServer.RestaurantHandler receives GET request
   ↓
6. Handler calls: RestaurantService.getAllRestaurants()
   ↓
7. RestaurantService gets from Database.restaurantService
   ↓
8. Returns: Map<Integer, Restaurant>
   ↓
9. Handler converts to JSON: [{id, name, cuisine, rating}]
   ↓
10. HTTP Response (200 OK) with JSON data
    ↓
11. Browser JavaScript receives JSON
    ↓
12. Display restaurants in grid layout
    ↓
13. User sees restaurants on page ✅
```

---

## 🎯 Object Lifecycle

### Creating an Order:

```
1. OrderService.createOrder(customerId, restaurantId, address)
   └─ creates: new Order(orderId, customerId, restaurantId, address)
      └─ sets: status = OrderStatus.PENDING
      └─ stores: orders.put(orderId, order)
      └─ returns: orderId

2. OrderService.addItemToOrder(orderId, itemId, name, price, qty)
   └─ gets: order = orders.get(orderId)
   └─ creates: new OrderItem(itemId, name, price, qty)
   └─ adds: order.addItem(item)
   └─ recalculates: order.calculateTotal()

3. PaymentService.processPayment(paymentId, amount)
   └─ gets: payment = paymentMethods.get(paymentId)
   └─ calls: payment.processPayment(amount) [Polymorphic]
   └─ returns: boolean (success/failure)

4. OrderService.updateOrderStatus(orderId, newStatus)
   └─ gets: order = orders.get(orderId)
   └─ sets: order.status = newStatus
   └─ if delivered: order.deliveryTime = LocalDateTime.now()

5. Order lifecycle complete! ✅
```

---

This visual guide helps understand how all components work together!
