package com.example.restaurantapi.dto;

public record RestaurantDtoResponse(
        long id, String name, String description,
        String phone, String address) {
}
