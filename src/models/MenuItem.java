package models;

public class MenuItem {
    private int itemId;
    private int restaurantId;
    private String itemName;
    private String description;
    private double price;
    private String category;
    private boolean isAvailable;

    public MenuItem(int itemId, int restaurantId, String itemName, String description, 
                   double price, String category, boolean isAvailable) {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getItemId() { return itemId; }
    public int getRestaurantId() { return restaurantId; }
    public String getItemName() { return itemName; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return isAvailable; }

    public void setPrice(double price) { this.price = price; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return itemName + " - Rs. " + price + " (" + category + ") - " + 
               (isAvailable ? "Available" : "Not Available");
    }
}
