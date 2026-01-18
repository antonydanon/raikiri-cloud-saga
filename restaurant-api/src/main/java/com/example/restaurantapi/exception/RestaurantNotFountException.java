package com.example.restaurantapi.exception;

public class RestaurantNotFountException extends RuntimeException {

    public RestaurantNotFountException(String message) {
        super(message);
    }
}
