package ui;

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

    private JPanel orderPanel;
    private JTable menuTable;
    private JButton orderButton;

    public FoodOrderingGUI() {
        setTitle("Online Food Ordering System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        initLoginPanel();
        initOrderPanel();

        add(loginPanel);
    }

    private void initLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));

        loginPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginPanel.add(new JLabel("")); 
        loginPanel.add(loginButton);

        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.RED);
        loginPanel.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.equals("admin") && password.equals("admin")) {
                    switchToOrderPanel();
                } else {
                    statusLabel.setText("Invalid credentials!");
                }
            }
        });
    }

    private void initOrderPanel() {
        orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout(10, 10));
        orderPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columns = {"ID", "Item Name", "Description", "Price ($)"};
        Object[][] data = {
            {"1", "Margherita Pizza", "Classic cheese and tomato", 12.99},
            {"2", "Burger", "Beef patty with lettuce", 8.50},
            {"3", "Pasta", "Penne in tomato sauce", 10.00},
            {"4", "Coke", "Cold beverage", 2.00}
        };

        menuTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(menuTable);
        orderPanel.add(scrollPane, BorderLayout.CENTER);

        orderButton = new JButton("Place Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = menuTable.getSelectedRow();
                if (selectedRow != -1) {
                    String itemName = (String) menuTable.getValueAt(selectedRow, 1);
                    JOptionPane.showMessageDialog(FoodOrderingGUI.this, 
                        "Successfully ordered: " + itemName + "!\nYour food will arrive soon.", 
                        "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(FoodOrderingGUI.this, 
                        "Please select an item from the menu to order.", 
                        "No Item Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(orderButton);
        orderPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        JLabel headerLabel = new JLabel("Welcome to Foodie Menu!");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderPanel.add(headerLabel, BorderLayout.NORTH);
    }

    private void switchToOrderPanel() {
        remove(loginPanel);
        add(orderPanel);
        revalidate();
        repaint();
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
