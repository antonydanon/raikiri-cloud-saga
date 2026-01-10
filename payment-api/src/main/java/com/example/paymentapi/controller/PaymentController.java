package com.example.paymentapi.controller;

import com.example.paymentapi.dto.PaymentRequest;
import com.example.paymentapi.dto.PaymentResponse;
import com.example.paymentapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> save(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok().body(paymentService.save(paymentRequest));
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        paymentService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
