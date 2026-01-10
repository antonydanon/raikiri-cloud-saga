package com.example.paymentapi.repository;

import com.example.paymentapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    void deleteByTxId(String txId);
}
