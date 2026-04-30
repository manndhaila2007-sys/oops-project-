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
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
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
