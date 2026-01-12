package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.OrderSaveDtoRequest;
import com.example.restaurantapi.dto.OrderSaveDtoResponse;
import com.example.restaurantapi.model.Order;
import com.example.restaurantapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderSaveDtoResponse save(@RequestBody OrderSaveDtoRequest request) {
        Order order = orderService.save(request.txId(), request.amount());
        return new OrderSaveDtoResponse(order.getId());
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        orderService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
