package ui;

import database.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodOrderingGUI extends JFrame {

    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;


    public FoodOrderingGUI() {
        setTitle("Online Food Ordering System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        initLoginPanel();

        add(loginPanel);
    }

    private void initLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(240, 244, 248)); // Light modern background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("🍔 Welcome to OFO");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        // Username Label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        // Username Field
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1, true),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1; gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        // Password Label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 2;
        loginPanel.add(passLabel, gbc);

        // Password Field
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1, true),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1; gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login Now");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Interactive Hover Effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(41, 128, 185)); // Darker blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(52, 152, 219)); // Back to original blue
            }
        });

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        // Status Label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setForeground(Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        loginPanel.add(statusLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (DBConnection.verifyLogin(username, password)) {
                    statusLabel.setForeground(new Color(0, 150, 0));
                    statusLabel.setText("Login successful! Starting web server...");
                    launchWebServer();
                } else {
                    statusLabel.setText("Invalid credentials!");
                }
            }
        });
    }

    private void launchWebServer() {
        // Disable login button to prevent clicking twice
        loginButton.setEnabled(false);
        
        // Run server in a new thread so GUI doesn't freeze
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Start the original web server via command line since it's in the default package
                    String currentDir = System.getProperty("user.dir");
                    Runtime.getRuntime().exec("java -cp . FoodOrderingServer", null, new java.io.File(currentDir));
                    
                    // Give the server 2 seconds to start
                    Thread.sleep(2000);
                    
                    // Open the default web browser to the localhost link
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new java.net.URI("http://localhost:8080"));
                    }
                    
                    // Close the Swing Login Window since we are on the web now
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                        }
                    });
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Error starting server!");
                    loginButton.setEnabled(true);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FoodOrderingGUI().setVisible(true);
            }
        });
    }
}
