package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.RestaurantDtoResponse;
import com.example.restaurantapi.dto.RestaurantSaveDtoRequest;
import com.example.restaurantapi.dto.RestaurantUpdateDtoRequest;
import com.example.restaurantapi.mapper.RestaurantMapper;
import com.example.restaurantapi.model.Restaurant;
import com.example.restaurantapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/{id}")
    public RestaurantDtoResponse getBy(@PathVariable Long id) {
        return RestaurantMapper.mapRestaurantToRestaurantDtoResponse(restaurantService.getBy(id));
    }

    @GetMapping
    public List<RestaurantDtoResponse> getAll() {
        List<Restaurant> restaurants = restaurantService.getAll();
        return restaurants.stream()
                .map(RestaurantMapper::mapRestaurantToRestaurantDtoResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RestaurantDtoResponse save(@RequestBody RestaurantSaveDtoRequest request) {
        Restaurant restaurant = restaurantService.save(
                request.name(), request.description(),
                request.phone(), request.address()
        );
        return RestaurantMapper.mapRestaurantToRestaurantDtoResponse(restaurant);
    }

    @PutMapping
    public RestaurantDtoResponse update(@RequestBody RestaurantUpdateDtoRequest request) {
        Restaurant restaurant = restaurantService.update(
                request.id(), request.name(), request.description(),
                request.phone(), request.address()
        );
        return RestaurantMapper.mapRestaurantToRestaurantDtoResponse(restaurant);
    }

    @DeleteMapping("/{id}")
    public String removeBy(@PathVariable Long id) {
        restaurantService.removeBy(id);
        return "Restaurant with id " + id + " has been successfully removed.";
    }
}
