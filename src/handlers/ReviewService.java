package handlers;

import models.*;
import java.util.*;

public class ReviewService {
    private Map<Integer, Review> reviews = new HashMap<>();
    private int reviewCounter = 1;

    public int addReview(int orderId, int customerId, int restaurantId, double rating, String comment) {
        if (rating < 1.0 || rating > 5.0) {
            return -1; // Invalid rating
        }
        Review review = new Review(reviewCounter, orderId, customerId, restaurantId, rating, comment);
        reviews.put(reviewCounter, review);
        return reviewCounter++;
    }

    public List<Review> getRestaurantReviews(int restaurantId) {
        List<Review> result = new ArrayList<>();
        for (Review review : reviews.values()) {
            if (review.getRestaurantId() == restaurantId) {
                result.add(review);
            }
        }
        return result;
    }

    public double getAverageRestaurantRating(int restaurantId) {
        List<Review> restaurantReviews = getRestaurantReviews(restaurantId);
        if (restaurantReviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Review review : restaurantReviews) {
            sum += review.getRating();
        }
        return sum / restaurantReviews.size();
    }

    public Review getReview(int reviewId) {
        return reviews.get(reviewId);
    }

    public List<Review> getCustomerReviews(int customerId) {
        List<Review> result = new ArrayList<>();
        for (Review review : reviews.values()) {
            if (review.getCustomerId() == customerId) {
                result.add(review);
            }
        }
        return result;
    }

    public List<Review> getAllReviews() {
        return new ArrayList<>(reviews.values());
    }

    public boolean deleteReview(int reviewId) {
        return reviews.remove(reviewId) != null;
    }
}
