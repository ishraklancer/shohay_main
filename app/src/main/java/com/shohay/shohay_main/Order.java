package com.shohay.shohay_main;

import android.widget.RatingBar;

public class Order {

    private String order_id, rating, type, order_timestamp, payment, status, delivery_timestamp;

    public Order(String order_id, String rating, String type, String order_timestamp, String payment, String status, String delivery_timestamp) {
        this.order_id = order_id;
        this.rating = rating;
        this.type = type;
        this.order_timestamp = order_timestamp;
        this.payment = payment;
        this.status = status;
        this.delivery_timestamp = delivery_timestamp;
    }

    public Order() {

    }

    public String getOrder_id() {
        return order_id;
    }

    public String getRating() {
        return rating;
    }

    public String getType() {
        return type;
    }

    public String getOrder_timestamp() {
        return order_timestamp;
    }

    public String getPayment() {
        return payment;
    }

    public String getStatus() {
        return status;
    }

    public String getDelivery_timestamp() {
        return delivery_timestamp;
    }
}
