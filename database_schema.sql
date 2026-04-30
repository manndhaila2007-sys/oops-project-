-- ============================================
-- OFO (Online Food Ordering) Database Schema
-- For Capstone Evaluation
-- ============================================

-- Create Users Table (Inheritance root for Customer/RestaurantOwner)
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    role VARCHAR(20) NOT NULL -- 'CUSTOMER' or 'OWNER'
);

-- Create Restaurants Table
CREATE TABLE restaurants (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    cuisine VARCHAR(50),
    address TEXT NOT NULL,
    owner_id INTEGER,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- Create Menu Items Table
CREATE TABLE menu_items (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    restaurant_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- Create Orders Table
CREATE TABLE orders (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- Create Order Items (Join table for Orders and Menu Items)
CREATE TABLE order_items (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    order_id INTEGER NOT NULL,
    menu_item_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 1,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);

-- ============================================
-- Dummy Data for Evaluation
-- ============================================

-- Insert default admin user for login page
INSERT INTO users (username, password, email, role) 
VALUES ('admin', 'admin', 'admin@ofo.com', 'OWNER');

-- Insert sample customer
INSERT INTO users (username, password, email, phone, role)
VALUES ('johndoe', 'password123', 'john@example.com', '1234567890', 'CUSTOMER');

-- Insert sample restaurant
INSERT INTO restaurants (name, cuisine, address, owner_id)
VALUES ('Spice Garden', 'Indian', 'Mumbai Central', 1);

-- Insert sample menu items
INSERT INTO menu_items (restaurant_id, name, description, price, category)
VALUES 
(1, 'Butter Chicken', 'Creamy butter chicken curry', 250.00, 'Non-Veg'),
(1, 'Paneer Tikka', 'Grilled paneer with spices', 180.00, 'Veg');
