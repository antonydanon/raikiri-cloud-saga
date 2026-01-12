package com.example.courierapi.controller;

import com.example.courierapi.dto.DeliverySaveDtoRequest;
import com.example.courierapi.dto.DeliverySaveDtoResponse;
import com.example.courierapi.model.Delivery;
import com.example.courierapi.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public DeliverySaveDtoResponse save(@RequestBody DeliverySaveDtoRequest request) {
        Delivery delivery = deliveryService.save(request.txId(), request.address());
        return new DeliverySaveDtoResponse(delivery.getId());
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        deliveryService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
