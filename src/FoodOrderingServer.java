import com.sun.net.httpserver.*;
import database.Database;
import handlers.*;
import models.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

public class FoodOrderingServer {
    private static final int PORT = 8080;
    private static Database db = Database.getInstance();

    public static void main(String[] args) throws IOException {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);
            
            // Create contexts for different endpoints
            server.createContext("/", new HomeHandler());
            server.createContext("/api/restaurants", new RestaurantHandler());
            server.createContext("/api/orders", new OrderHandler());
            server.createContext("/api/users", new UserHandler());
            server.createContext("/api/reviews", new ReviewHandler());

            server.setExecutor(null); // Default executor
            server.start();

            System.out.println("\n========================================");
            System.out.println("🍔 OFO - Online Food Ordering System");
            System.out.println("========================================");
            System.out.println("\n✅ Server running successfully!");
            System.out.println("\n🌐 Access URL: http://localhost:8080");
            System.out.println("\n📝 Endpoints:");
            System.out.println("   - http://localhost:8080");
            System.out.println("   - http://localhost:8080/api/restaurants");
            System.out.println("   - http://localhost:8080/api/orders");
            System.out.println("\n⏸️  Press Ctrl+C to stop the server");
            System.out.println("========================================\n");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR: Failed to start server!");
            System.err.println("Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Home Page Handler
    static class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = getHomePage();
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    // Restaurant Handler
    static class RestaurantHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String query = exchange.getRequestURI().getQuery();

            if (path.equals("/api/restaurants") && exchange.getRequestMethod().equals("GET")) {
                String response = getRestaurantsJSON();
                sendResponse(exchange, response, "application/json");
            }
        }
    }

    // Order Handler
    static class OrderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/api/orders") && exchange.getRequestMethod().equals("GET")) {
                String response = getOrdersJSON();
                sendResponse(exchange, response, "application/json");
            }
        }
    }

    // User Handler
    static class UserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{}";
            sendResponse(exchange, response, "application/json");
        }
    }

    // Review Handler
    static class ReviewHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "[]";
            sendResponse(exchange, response, "application/json");
        }
    }

    static void sendResponse(HttpExchange exchange, String response, String contentType) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType + "; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    static String getHomePage() {
        return "<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>OFO - Online Food Ordering</title><style>body{font-family:Arial,sans-serif;background:linear-gradient(135deg,#667eea 0%,#764ba2 100%);margin:0;padding:20px;min-height:100vh}header{background:rgba(0,0,0,0.8);color:white;padding:20px;text-align:center;border-radius:10px}h1{margin:0;color:#fff}p{margin:10px 0;color:#ddd}.container{max-width:1200px;margin:30px auto;background:white;padding:30px;border-radius:10px;box-shadow:0 10px 30px rgba(0,0,0,0.3)}h2{color:#667eea;border-bottom:3px solid #667eea;padding-bottom:10px}.grid{display:grid;grid-template-columns:repeat(auto-fill,minmax(280px,1fr));gap:20px;margin:20px 0}.card{border:1px solid #ddd;border-radius:10px;padding:20px;box-shadow:0 2px 10px rgba(0,0,0,0.1)}.card h3{color:#667eea;margin-top:0}.rating{color:#ff9800;font-weight:bold}button{background:#667eea;color:white;border:none;padding:10px 20px;border-radius:5px;cursor:pointer}button:hover{background:#764ba2}.info-box{background:#f0f4ff;border-left:5px solid #667eea;padding:20px;margin:20px 0;border-radius:5px}footer{text-align:center;margin-top:40px;color:#666}pre{background:#f5f5f5;padding:15px;border-radius:5px;overflow-x:auto}</style></head><body><header><h1>🍔 OFO - Online Food Ordering System</h1><p>Java OOP Project | Complete Backend & Frontend</p></header><div class='container'><h2>✅ Server is Running!</h2><div class='info-box'><h3>🌐 Access Points:</h3><p><strong>Main Website:</strong> <a href='http://localhost:8080'>http://localhost:8080</a></p><p><strong>Restaurants API:</strong> <a href='http://localhost:8080/api/restaurants'>http://localhost:8080/api/restaurants</a></p><p><strong>Orders API:</strong> <a href='http://localhost:8080/api/orders'>http://localhost:8080/api/orders</a></p></div><h2>🏪 Available Restaurants</h2><div id='restaurants'></div><h2>📦 Recent Orders</h2><div id='orders'></div><h2>📚 About This Project</h2><div class='info-box'><h3>OOP Concepts Used:</h3><ul><li><strong>Inheritance:</strong> User → Customer, RestaurantOwner</li><li><strong>Polymorphism:</strong> PaymentMethod interface with 3 implementations</li><li><strong>Encapsulation:</strong> Private members with getters/setters</li><li><strong>Abstraction:</strong> Abstract classes and interfaces</li><li><strong>Collections:</strong> HashMap, ArrayList with Generics</li><li><strong>Design Pattern:</strong> Singleton, Service Layer, MVC</li></ul></div><h2>🎯 Features</h2><div class='grid'><div class='card'><h3>👥 User Management</h3><p>Registration, login, profile management</p></div><div class='card'><h3>🍽️ Restaurants</h3><p>Browse & search restaurants by cuisine</p></div><div class='card'><h3>🛒 Orders</h3><p>Place, track, and manage orders</p></div><div class='card'><h3>💳 Payments</h3><p>Card, Wallet, and Cash on Delivery</p></div><div class='card'><h3>⭐ Reviews</h3><p>Rate and review restaurants</p></div><div class='card'><h3>💰 Wallet</h3><p>Digital wallet for fast payments</p></div></div></div><footer><p>&copy; 2024 Online Food Ordering System | Java OOP Project</p></footer><script>fetch('/api/restaurants').then(r=>r.json()).then(data=>{let html='<div class=\"grid\">';data.forEach(r=>{html+='<div class=\"card\"><h3>'+r.name+'</h3><p><strong>Cuisine:</strong> '+r.cuisine+'</p><p><strong>Address:</strong> '+r.address+'</p><p class=\"rating\">⭐ '+r.rating+'</p></div>'});html+='</div>';document.getElementById('restaurants').innerHTML=html}).catch(e=>console.log('Restaurants loading...'));fetch('/api/orders').then(r=>r.json()).then(data=>{let html='<div class=\"grid\">';if(data.length>0){data.forEach(o=>{html+='<div class=\"card\"><p><strong>Order #'+o.id+'</strong></p><p>Status: '+o.status+'</p><p>Total: Rs. '+o.total.toFixed(2)+'</p></div>'});html+='</div>'}else{html='<p>No orders yet</p>'}document.getElementById('orders').innerHTML=html}).catch(e=>console.log('Orders loading...'))</script></body></html>";
    }

    static String getRestaurantsJSON() {
        List<Restaurant> restaurants = db.restaurantService.getAllRestaurants();
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant r = restaurants.get(i);
            json.append("{");
            json.append("\"id\":").append(r.getRestaurantId()).append(",");
            json.append("\"name\":\"").append(r.getRestaurantName()).append("\",");
            json.append("\"cuisine\":\"").append(r.getCuisine()).append("\",");
            json.append("\"address\":\"").append(r.getAddress()).append("\",");
            json.append("\"rating\":").append(r.getRating());
            json.append("}");
            if (i < restaurants.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }

    static String getOrdersJSON() {
        List<Order> orders = db.orderService.getAllOrders();
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            json.append("{");
            json.append("\"id\":").append(o.getOrderId()).append(",");
            json.append("\"customerId\":").append(o.getCustomerId()).append(",");
            json.append("\"status\":\"").append(o.getStatus()).append("\",");
            json.append("\"total\":").append(o.getTotalPrice());
            json.append("}");
            if (i < orders.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }
}
