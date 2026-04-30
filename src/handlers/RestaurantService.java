package handlers;

import models.*;
import java.util.*;

public class RestaurantService {
    private Map<Integer, Restaurant> restaurants = new HashMap<>();
    private int restaurantCounter = 1;
    private int menuItemCounter = 1;

    public int addRestaurant(String name, String cuisine, String address) {
        Restaurant restaurant = new Restaurant(restaurantCounter, name, cuisine, address);
        restaurants.put(restaurantCounter, restaurant);
        return restaurantCounter++;
    }

    public Restaurant getRestaurant(int restaurantId) {
        return restaurants.get(restaurantId);
    }

    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(restaurants.values());
    }

    public List<Restaurant> searchRestaurantsByCuisine(String cuisine) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant r : restaurants.values()) {
            if (r.getCuisine().equalsIgnoreCase(cuisine) && r.isOpen()) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Restaurant> searchRestaurantsByName(String name) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant r : restaurants.values()) {
            if (r.getRestaurantName().toLowerCase().contains(name.toLowerCase()) && r.isOpen()) {
                result.add(r);
            }
        }
        return result;
    }

    public int addMenuItemToRestaurant(int restaurantId, String itemName, String description, 
                                       double price, String category) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            MenuItem item = new MenuItem(menuItemCounter, restaurantId, itemName, description, 
                                        price, category, true);
            restaurant.addMenuItem(item);
            return menuItemCounter++;
        }
        return -1;
    }

    public MenuItem getMenuItem(int restaurantId, int itemId) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            return restaurant.getMenuItem(itemId);
        }
        return null;
    }

    public List<MenuItem> getRestaurantMenu(int restaurantId) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            return restaurant.getAllMenuItems();
        }
        return new ArrayList<>();
    }

    public List<MenuItem> getMenuItemsByCategory(int restaurantId, String category) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            return restaurant.getMenuItemsByCategory(category);
        }
        return new ArrayList<>();
    }

    public void updateMenuItemPrice(int restaurantId, int itemId, double newPrice) {
        MenuItem item = getMenuItem(restaurantId, itemId);
        if (item != null) {
            item.setPrice(newPrice);
        }
    }

    public void setRestaurantStatus(int restaurantId, boolean isOpen) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            restaurant.setOpen(isOpen);
        }
    }

    public void updateRestaurantRating(int restaurantId, double newRating) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null && newRating >= 1.0 && newRating <= 5.0) {
            restaurant.setRating(newRating);
        }
    }
}
