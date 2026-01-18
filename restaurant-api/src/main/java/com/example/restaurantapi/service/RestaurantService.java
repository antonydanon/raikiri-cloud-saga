package com.example.restaurantapi.service;

import com.example.restaurantapi.exception.RestaurantNotFountException;
import com.example.restaurantapi.model.Restaurant;
import com.example.restaurantapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Cacheable(value = "restaurant", key = "#id")
    public Restaurant getBy(long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFountException("Restaurant with id " + id + " was not found."));
    }

    public Restaurant save(String name, String description, String phone, String address) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setPhone(phone);
        restaurant.setAddress(address);
        restaurant.setIsActive(true);
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurant", key = "#id")
    public Restaurant update(long id, String name, String description, String phone, String address) {
        Restaurant restaurant = getBy(id);
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setPhone(phone);
        restaurant.setAddress(address);
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurant", key = "#id")
    public void removeBy(Long id) {
        restaurantRepository.deleteById(id);
    }
}
