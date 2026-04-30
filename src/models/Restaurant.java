package models;

import java.util.*;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private String cuisine;
    private String address;
    private double rating;
    private Map<Integer, MenuItem> menuItems;
    private boolean isOpen;

    public Restaurant(int restaurantId, String restaurantName, String cuisine, String address) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
        this.address = address;
        this.rating = 4.5;
        this.menuItems = new HashMap<>();
        this.isOpen = true;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.put(item.getItemId(), item);
    }

    public MenuItem getMenuItem(int itemId) {
        return menuItems.get(itemId);
    }

    public List<MenuItem> getAllMenuItems() {
        return new ArrayList<>(menuItems.values());
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems.values()) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }

    // Getters and Setters
    public int getRestaurantId() { return restaurantId; }
    public String getRestaurantName() { return restaurantName; }
    public String getCuisine() { return cuisine; }
    public String getAddress() { return address; }
    public double getRating() { return rating; }
    public boolean isOpen() { return isOpen; }

    public void setRating(double rating) { this.rating = rating; }
    public void setOpen(boolean open) { isOpen = open; }

    @Override
    public String toString() {
        return restaurantName + " (" + cuisine + ") - Rating: " + rating + " ⭐";
    }
}
