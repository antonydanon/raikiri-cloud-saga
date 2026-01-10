package com.example.courierapi.controller;

import com.example.courierapi.dto.DeliveryRequest;
import com.example.courierapi.dto.DeliveryResponse;
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
    public ResponseEntity<DeliveryResponse> save(@RequestBody DeliveryRequest deliveryRequest) {
        return ResponseEntity.ok(deliveryService.save(deliveryRequest));
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        deliveryService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
