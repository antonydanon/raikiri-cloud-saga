package com.example.courierapi.repository;

import com.example.courierapi.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    void deleteByTxId(String txId);
}
