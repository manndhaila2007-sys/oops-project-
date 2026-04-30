package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:foodordering.db";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean verifyLogin(String username, String password) {
        Connection conn = getConnection();
        
        // Fallback for Capstone if SQLite JDBC driver is not installed
        if (conn == null) {
            System.err.println("JDBC Driver missing or DB not found. Using fallback authentication.");
            // Accept any non-empty login for the demo if DB is offline
            return username != null && !username.trim().isEmpty() && password != null && !password.isEmpty();
        }

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Login successful for user: " + username);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error verifying login: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public static void fetchMenu() {
        String query = "SELECT id, name, price FROM menu_items";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("--- Database Menu ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + 
                                   rs.getString("name") + " - $" + 
                                   rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching menu: " + e.getMessage());
        }
    }
}
