package com.example.restaurantapi.mapper;

import com.example.restaurantapi.dto.RestaurantDtoResponse;
import com.example.restaurantapi.model.Restaurant;

public class RestaurantMapper {

    public static RestaurantDtoResponse mapRestaurantToRestaurantDtoResponse(Restaurant restaurant) {
        return new RestaurantDtoResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getPhone(),
                restaurant.getAddress()
        );
    }
}
