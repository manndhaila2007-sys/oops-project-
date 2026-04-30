package handlers;

import models.*;
import java.util.*;

public class UserService {
    private Map<Integer, User> users = new HashMap<>();
    private int userCounter = 1;

    public boolean registerCustomer(String name, String email, String phone, String address, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false; // Email already exists
            }
        }
        Customer customer = new Customer(userCounter++, name, email, phone, address, password);
        users.put(customer.getUserId(), customer);
        return true;
    }

    public boolean registerRestaurantOwner(String name, String email, String phone, String address, 
                                          String password, int restaurantId) {
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        RestaurantOwner owner = new RestaurantOwner(userCounter++, name, email, phone, address, password, restaurantId);
        users.put(owner.getUserId(), owner);
        return true;
    }

    public User loginUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }

    public boolean updateUserProfile(int userId, String name, String phone, String address) {
        User user = users.get(userId);
        if (user != null) {
            user.setName(name);
            user.setPhone(phone);
            user.setAddress(address);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public boolean userExists(int userId) {
        return users.containsKey(userId);
    }
}
