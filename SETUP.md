# Setup Guide - Online Food Ordering System (OFO)

## Prerequisites

### System Requirements
- Windows 7 or higher (or Linux/Mac with Java installed)
- Java JDK 8 or higher
- Modern web browser (Chrome, Firefox, Edge, Safari)
- Minimum 2GB RAM
- 500MB free disk space

### Check Java Installation

Open Command Prompt and run:
```bash
java -version
javac -version
```

You should see Java version information. If not, install Java from: https://www.oracle.com/java/technologies/downloads/

---

## Installation Steps

### Step 1: Extract Project Files

Make sure all files are in this structure:
```
ofo/
├── src/
│   ├── models/
│   ├── handlers/
│   ├── database/
│   └── FoodOrderingServer.java
├── web/
│   └── index.html
├── RUN_PROJECT.bat
└── README.md
```

### Step 2: Run the Project

**Windows Users:**
1. Double-click `RUN_PROJECT.bat`
2. Wait for compilation to complete
3. You'll see: "Access the website at: http://localhost:8080"

**Linux/Mac Users:**
```bash
# Open terminal in project directory
chmod +x run.sh
./run.sh

# Or manually:
mkdir -p bin
javac -d bin src/models/*.java
javac -cp bin -d bin src/handlers/*.java src/database/*.java
javac -cp bin -d bin src/FoodOrderingServer.java
cd bin
java -cp . FoodOrderingServer
```

### Step 3: Access Website

Open your web browser and go to:
```
http://localhost:8080
```

---

## Project Components

### 1. Java Models (src/models/)

**User Hierarchy:**
```
User (Abstract)
├── Customer
└── RestaurantOwner
```

**Payment Methods:**
```
PaymentMethod (Interface)
├── CardPayment
├── WalletPayment
└── CODPayment
```

**Core Entities:**
- Restaurant
- MenuItem
- Order
- OrderItem
- Review

### 2. Service Layer (src/handlers/)

- **UserService** - Manages user registration and authentication
- **RestaurantService** - Handles restaurant and menu operations
- **OrderService** - Manages order creation and tracking
- **PaymentService** - Processes payments
- **ReviewService** - Manages reviews and ratings

### 3. Database (src/database/)

- **Database.java** - Singleton pattern for data management
- In-memory storage (HashMap & ArrayList)
- Pre-loaded sample data

### 4. Web Interface (web/)

- **index.html** - Single-page application with:
  - Navigation tabs
  - Restaurant browsing
  - Order tracking
  - Project information

---

## Features Overview

### 🏠 Home Section
- Welcome message
- Feature highlights
- Quick overview

### 🏪 Restaurants Section
- Browse all available restaurants
- Search by name or cuisine
- View ratings
- Access menu items

### 📦 Orders Section
- View order history
- Check order status (Pending, Confirmed, Preparing, Ready, Delivered)
- View order details and total

### ℹ️ About Section
- OOP concepts explanation
- Project structure
- API documentation
- Key features list

---

## API Endpoints

### GET /api/restaurants
Returns all restaurants in JSON format:
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

### GET /api/orders
Returns all orders in JSON format:
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

---

## Troubleshooting

### Error: "javac is not recognized"
**Cause:** Java is not installed or not in system PATH
**Solution:**
1. Install Java from https://www.oracle.com/java/technologies/downloads/
2. Add Java to PATH:
   - Windows: Set environment variable JAVA_HOME
   - Linux/Mac: Add to ~/.bashrc or ~/.zshrc

### Error: "Address already in use"
**Cause:** Port 8080 is already in use
**Solution:**
1. Find and kill process using port 8080
2. Or modify port in FoodOrderingServer.java (line ~18):
   ```java
   private static final int PORT = 8081; // Change to different port
   ```

### Error: "Cannot find symbol"
**Cause:** Compilation order issue or missing files
**Solution:**
1. Delete bin folder: `rmdir /s bin`
2. Recompile using RUN_PROJECT.bat or manual commands

### Website shows blank page
**Cause:** Server not fully started
**Solution:**
1. Check console for errors
2. Wait 5 seconds and refresh page
3. Try incognito/private browsing mode

### Changes not reflected in website
**Cause:** Browser cache
**Solution:**
1. Hard refresh: Ctrl+Shift+R (Windows) or Cmd+Shift+R (Mac)
2. Or clear browser cache

---

## Modifying the Project

### Add a New Restaurant

Edit `src/database/Database.java`:
```java
int newRestaurant = restaurantService.addRestaurant(
    "Restaurant Name", 
    "Cuisine Type", 
    "Address"
);
```

### Add Menu Items

```java
restaurantService.addMenuItemToRestaurant(
    restaurantId, 
    "Item Name", 
    "Description", 
    price, 
    "Category"
);
```

### Add New Features

1. Create new model class in `src/models/`
2. Create service class in `src/handlers/`
3. Add endpoints in `FoodOrderingServer.java`
4. Update `web/index.html` for frontend

---

## Performance Tips

### For Development:
- Keep terminal window open
- Use browser developer tools (F12)
- Check console for errors

### For Production:
- Use a proper database (MySQL/PostgreSQL)
- Add authentication and security
- Use HTTPS
- Implement caching
- Add rate limiting

---

## Security Considerations

**Current System (For Learning):**
- Plain text passwords (NOT SAFE for production)
- In-memory storage (data lost on restart)
- No authentication tokens
- No input validation

**For Production, Add:**
- Password hashing (BCrypt)
- Database persistence
- JWT authentication
- Input validation and sanitization
- HTTPS encryption
- Rate limiting
- SQL injection prevention

---

## Directory Permissions

Ensure the following permissions are set:

**Windows:** No special permissions needed
**Linux/Mac:**
```bash
chmod 755 bin/
chmod 644 src/**/*.java
chmod 644 web/index.html
```

---

## Running on Different Ports

To run on a different port (e.g., 8081):

1. Edit `FoodOrderingServer.java`:
```java
private static final int PORT = 8081; // Change this
```

2. Recompile and run
3. Access at `http://localhost:8081`

---

## System Architecture

```
┌─────────────────────────────────────────────────┐
│         Web Browser (Frontend)                  │
│  HTML/CSS/JavaScript Interface                  │
└────────────────────┬────────────────────────────┘
                     │ HTTP Requests/Responses
                     │ http://localhost:8080
┌────────────────────▼────────────────────────────┐
│   Java HTTP Server (Backend)                    │
│   FoodOrderingServer.java                       │
├─────────────────────────────────────────────────┤
│   Service Layer (Business Logic)                │
│  ├─ UserService                                 │
│  ├─ RestaurantService                           │
│  ├─ OrderService                                │
│  ├─ PaymentService                              │
│  └─ ReviewService                               │
├─────────────────────────────────────────────────┤
│   Model Layer (Data Objects)                    │
│  ├─ User Hierarchy                              │
│  ├─ Restaurant & Menu                           │
│  ├─ Order Management                            │
│  └─ Payment Methods                             │
├─────────────────────────────────────────────────┤
│   Database Layer (In-Memory Storage)            │
│   Database.java (Singleton)                     │
│  ├─ HashMap collections                         │
│  └─ ArrayList collections                       │
└─────────────────────────────────────────────────┘
```

---

## Support & Debugging

### Enable Debug Mode

Add this to `FoodOrderingServer.java`:
```java
System.out.println("DEBUG: Starting request processing");
```

### Check Server Logs

Monitor console output for:
- Compilation messages
- Server startup status
- API request logs
- Error messages

### Common Log Messages

```
✓ Compilation successful! → Code compiled correctly
✓ COMPILATION SUCCESSFUL! → Ready to start
✓ Server running at: http://localhost:8080 → Access website
✗ ERROR: Compilation failed → Fix code syntax
✗ Address already in use → Change port or kill process
```

---

## Next Steps

After getting the system running:

1. **Explore the Code** - Read comments and understand the OOP concepts
2. **Modify Features** - Add new functionality
3. **Extend Database** - Add more restaurants and menu items
4. **Enhance UI** - Improve the web interface
5. **Add Features** - Implement new order features
6. **Optimize Performance** - Improve code efficiency

---

## Resources

- Java Tutorials: https://docs.oracle.com/javase/tutorial/
- OOP Concepts: https://en.wikipedia.org/wiki/Object-oriented_programming
- HTTP Server: https://docs.oracle.com/javase/10/docs/api/com/sun/net/httpserver/HttpServer.html
- HTML/CSS/JavaScript: https://developer.mozilla.org/en-US/

---

## Getting Help

If you encounter issues:

1. Check the error message carefully
2. Review the Troubleshooting section above
3. Check file permissions and paths
4. Verify Java installation
5. Try restarting the server
6. Check system logs in console

---

**Happy Learning! 🚀**

For the best experience, keep this guide handy while running the project.
