package com.example.restaurantapi.service;

import com.example.restaurantapi.model.Order;
import com.example.restaurantapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order save(String txId, BigDecimal amount) {
        Order order = new Order();
        order.setTxId(txId);
        order.setAmount(amount);
        return orderRepository.save(order);
    }

    @Transactional
    public void rollbackToSave(String txId) {
        orderRepository.deleteByTxId(txId);
    }
}
