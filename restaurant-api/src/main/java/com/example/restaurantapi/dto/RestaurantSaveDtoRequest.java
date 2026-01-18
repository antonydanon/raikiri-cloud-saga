package com.example.restaurantapi.dto;

public record RestaurantSaveDtoRequest(
        String name, String description,
        String phone, String address) {
}
