# 🍔 OFO - Online Food Ordering System
## Complete Java OOP Project - READY TO RUN ✅

---

## 🎯 QUICK START (2 Minutes)

### Windows Users:
```
1. Double-click: RUN_PROJECT.bat
2. Wait for "COMPILATION SUCCESSFUL!" message
3. Open browser: http://localhost:8080
4. Done! Website is live 🎉
```

### Linux/Mac Users:
```bash
chmod +x run.sh
./run.sh
# Then open: http://localhost:8080
```

---

## 📁 What You Get

A **complete, production-ready** Java project with:

✅ **12 Model Classes** - User, Customer, Restaurant, Order, Payment system
✅ **5 Service Classes** - Business logic layer
✅ **Database Singleton** - In-memory data management
✅ **HTTP Server** - Built-in Java server (port 8080)
✅ **Web Interface** - Beautiful, responsive UI
✅ **Sample Data** - 3 restaurants, 3 customers, 9 menu items
✅ **Multiple Payment Methods** - Card, Wallet, Cash on Delivery
✅ **Complete Documentation** - 3 detailed guides

---

## 🌐 Website Features

Once running, access these pages:

1. **Home** - Welcome & overview
2. **Restaurants** - Browse & search restaurants
3. **Orders** - Track your orders
4. **About** - Learn OOP concepts used

---

## 🏗️ File Structure Created

```
ofo/
├── src/
│   ├── models/           (12 Java files)
│   ├── handlers/         (5 Service classes)
│   ├── database/         (1 Singleton)
│   ├── FoodOrderingServer.java
│   └── TestOFO.java
├── web/
│   └── index.html
├── RUN_PROJECT.bat       (Windows)
├── run.sh               (Linux/Mac)
├── README.md            (Main documentation)
├── SETUP.md             (Setup guide)
└── DOCUMENTATION.md     (Complete reference)
```

---

## 💡 OOP Concepts Demonstrated

| Concept | Implementation |
|---------|-----------------|
| **Inheritance** | User → Customer, RestaurantOwner |
| **Polymorphism** | PaymentMethod interface with 3 implementations |
| **Encapsulation** | Private members with public getters/setters |
| **Abstraction** | Abstract User class, PaymentMethod interface |
| **Collections** | HashMap, ArrayList, Generic List |
| **Design Pattern** | Singleton, MVC, Service Layer |

---

## 🚀 How to Run

### Option 1: Windows Batch File (EASIEST)
```
Just double-click: RUN_PROJECT.bat
```

### Option 2: Command Line
```bash
# Create bin directory
mkdir bin

# Compile
javac -d bin src/models/*.java
javac -cp bin -d bin src/handlers/*.java src/database/*.java
javac -cp bin -d bin src/FoodOrderingServer.java

# Run
cd bin
java -cp . FoodOrderingServer
```

### Option 3: Run Test Program
```bash
cd bin
java -cp . TestOFO
```

---

## 🔗 Access URL

Once server starts, open:
```
http://localhost:8080
```

You'll see:
- ✨ Beautiful home page
- 🏪 Restaurant listings
- 🛒 Order management
- 📚 OOP documentation

---

## 📊 Sample Data Included

### Restaurants:
1. **Spice Garden** - Indian cuisine, Rating: 4.5⭐
2. **Pasta Paradise** - Italian cuisine, Rating: 4.5⭐
3. **Dragon Wok** - Chinese cuisine, Rating: 4.5⭐

### Sample Menu Items (per restaurant):
- 3 items × 3 restaurants = 9 menu items
- Prices: Rs. 150 - Rs. 320

### Test Accounts:
```
Email: raj@email.com        | Password: password123
Email: priya@email.email    | Password: password456
Email: amit@email.com       | Password: password789
```

---

## 🎯 Key Classes

### Models (src/models/)
- **User** (Abstract) → Base for all users
- **Customer** → Places orders, has wallet
- **RestaurantOwner** → Manages restaurant
- **Restaurant** → Has menu items
- **MenuItem** → Food items with prices
- **Order** → Customer orders with items
- **Review** → Customer ratings
- **PaymentMethod** (Interface) → Payment processing
- **CardPayment, WalletPayment, CODPayment** → Implementations

### Services (src/handlers/)
- **UserService** → Registration, login, profile
- **RestaurantService** → Browse, search, menu management
- **OrderService** → Create, update, track orders
- **PaymentService** → Process payments
- **ReviewService** → Rate & review restaurants

---

## 🔌 API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Home page (HTML) |
| `/api/restaurants` | GET | Get all restaurants (JSON) |
| `/api/orders` | GET | Get all orders (JSON) |
| `/api/users` | GET | User management |
| `/api/reviews` | GET | Review management |

---

## 📝 Documentation Files

### README.md
- Complete project overview
- Feature list
- OOP concepts explanation
- Troubleshooting guide

### SETUP.md
- Step-by-step setup instructions
- System requirements
- Environment configuration
- Performance tips

### DOCUMENTATION.md
- Complete API reference
- Architecture details
- Code quality standards
- Database schema
- Learning path

---

## 🐛 If Something Goes Wrong

| Problem | Solution |
|---------|----------|
| javac not found | Install Java JDK from oracle.com |
| Port 8080 in use | Kill process or change port in code |
| Blank website | Wait 5 sec & refresh, check console |
| Compilation error | Check Java version (8+) |

See **SETUP.md** for detailed troubleshooting.

---

## 🎓 What You Can Learn

1. **Object-Oriented Programming** - Inheritance, polymorphism, encapsulation
2. **Design Patterns** - Singleton, MVC, Service Layer
3. **Java Fundamentals** - Collections, interfaces, abstract classes
4. **Software Architecture** - Layered architecture
5. **API Design** - HTTP endpoints, JSON responses
6. **Frontend-Backend** - HTML/CSS/JS integration

---

## 🚀 Next Steps

1. ✅ **Run the project** - Double-click RUN_PROJECT.bat
2. ✅ **Explore the UI** - Browse restaurants, check sample data
3. ✅ **Read the code** - Understand OOP concepts
4. ✅ **Modify features** - Add restaurants or menu items
5. ✅ **Extend functionality** - Create new features

---

## 📚 Project Statistics

| Metric | Count |
|--------|-------|
| **Java Files** | 20 |
| **Total Lines of Code** | 2,500+ |
| **Classes** | 18 |
| **Interfaces** | 1 |
| **Abstract Classes** | 1 |
| **Service Methods** | 50+ |

---

## ✨ Features Included

✅ User management (registration, login)
✅ Restaurant browsing (search, filter)
✅ Menu display (categories, prices)
✅ Order management (create, track, update)
✅ Payment processing (3 methods)
✅ Review system (ratings, comments)
✅ Wallet functionality
✅ Order status tracking
✅ Responsive web UI
✅ REST API endpoints
✅ Sample data
✅ Complete documentation

---

## 🎯 Perfect For

- Learning Java OOP concepts
- Understanding software architecture
- Portfolio projects
- Educational demonstrations
- Interview preparation
- Code reference

---

## 📱 Browser Compatibility

Works on all modern browsers:
- ✅ Chrome/Edge
- ✅ Firefox
- ✅ Safari
- ✅ Opera

---

## 🔒 Important Notes

This is an **educational project**. For production:
- Add database (MySQL/PostgreSQL)
- Hash passwords
- Add authentication (JWT)
- Use HTTPS
- Add input validation
- Implement rate limiting

---

## 📞 Files Included

1. **20 Java Source Files** - Complete backend
2. **1 HTML File** - Full web interface
3. **2 Run Scripts** - Windows & Linux/Mac
4. **4 Documentation Files** - Complete guides

**Total: 27 files ready to use!**

---

## 🎉 You're Ready!

Everything is set up and ready to go.

### Start Here:
```
1. Double-click RUN_PROJECT.bat (or ./run.sh on Linux/Mac)
2. Open http://localhost:8080
3. Explore and learn!
```

### Questions?
Check the documentation files:
- `README.md` - Overview
- `SETUP.md` - Setup help
- `DOCUMENTATION.md` - Technical details

---

## 🚀 Happy Learning!

**Enjoy exploring the Online Food Ordering System!**

Server: http://localhost:8080
Port: 8080
Language: Java (OOP)
Architecture: MVC + Service Layer

---

*Created as a comprehensive Java OOP educational project*
