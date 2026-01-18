package com.example.restaurantapi.dto;

public record RestaurantUpdateDtoRequest(
        long id, String name, String description,
        String phone, String address) {
}
