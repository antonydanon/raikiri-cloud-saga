package com.example.paymentapi.service;

import com.example.paymentapi.model.Payment;
import com.example.paymentapi.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private static final double PROBABILITY_OF_FAILURE = 0.4;

    private final PaymentRepository paymentRepository;

    public Payment save(String txId, BigDecimal amount) {
        simulateError();
        Payment payment = new Payment();
        payment.setTxId(txId);
        payment.setAmount(amount);
        return paymentRepository.save(payment);
    }

    @Transactional
    public void rollbackToSave(String txId) {
        paymentRepository.deleteByTxId(txId);
    }

    private void simulateError() {
        if (Math.random() < PROBABILITY_OF_FAILURE) {
            throw new RuntimeException("An unexpected error has occurred!");
        }
    }
}
