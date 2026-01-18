package com.example.restaurantapi.exception;

import com.example.restaurantapi.exception.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RestaurantNotFountException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantNotFound(RestaurantNotFountException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }
}
