package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.OrderRequest;
import com.example.restaurantapi.dto.OrderResponse;
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
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok().body(orderService.save(orderRequest));
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        orderService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
