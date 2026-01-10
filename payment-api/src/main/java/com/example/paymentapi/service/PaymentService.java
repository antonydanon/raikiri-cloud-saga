package com.example.paymentapi.service;

import com.example.paymentapi.dto.PaymentRequest;
import com.example.paymentapi.dto.PaymentResponse;
import com.example.paymentapi.model.Payment;
import com.example.paymentapi.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private static final double PROBABILITY_OF_FAILURE = 0.4;

    private final PaymentRepository paymentRepository;

    public PaymentResponse save(PaymentRequest paymentRequest) {
        simulateError();
        Payment payment = new Payment();
        payment.setTxId(paymentRequest.getTxId());
        payment.setAmount(paymentRequest.getAmount());
        Payment savedPayment = paymentRepository.save(payment);
        return new PaymentResponse(savedPayment.getId());
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
