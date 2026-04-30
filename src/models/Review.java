package models;

import java.time.LocalDateTime;

public class Review {
    private int reviewId;
    private int orderId;
    private int customerId;
    private int restaurantId;
    private double rating;
    private String comment;
    private LocalDateTime reviewDate;

    public Review(int reviewId, int orderId, int customerId, int restaurantId, 
                 double rating, String comment) {
        this.reviewId = reviewId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = LocalDateTime.now();
    }

    // Getters
    public int getReviewId() { return reviewId; }
    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public int getRestaurantId() { return restaurantId; }
    public double getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getReviewDate() { return reviewDate; }

    @Override
    public String toString() {
        return "Review: " + rating + "⭐ - " + comment;
    }
}
