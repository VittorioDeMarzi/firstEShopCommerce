package de.supercode.eCommerce.entities.enums;

public enum OrderStatus {
    PENDING_PAYMENT("Pending Payment"),
    PAYMENT_ACCEPTED("Payment Accepted"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled"),
    RETURN_IN_PROGRESS("Return in Progress"),
    REFUNDED("Refunded");

    private final String displayStatus;

    // constructor
    OrderStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    // getter
    public String getDisplayStatus() {
        return displayStatus;
    }



}
