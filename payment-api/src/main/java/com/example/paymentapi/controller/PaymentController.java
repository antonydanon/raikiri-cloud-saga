package com.example.paymentapi.controller;

import com.example.paymentapi.dto.PaymentSaveDtoRequest;
import com.example.paymentapi.dto.PaymentSaveDtoResponse;
import com.example.paymentapi.model.Payment;
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
    public PaymentSaveDtoResponse save(@RequestBody PaymentSaveDtoRequest request) {
        Payment payment = paymentService.save(request.txId(), request.amount());
        return new PaymentSaveDtoResponse(payment.getId());
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        paymentService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
