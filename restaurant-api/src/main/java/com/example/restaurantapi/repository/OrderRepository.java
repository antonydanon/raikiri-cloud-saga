package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteByTxId(String txId);
}
