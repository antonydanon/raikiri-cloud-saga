package com.example.restaurantapi.service;

import com.example.restaurantapi.dto.OrderRequest;
import com.example.restaurantapi.dto.OrderResponse;
import com.example.restaurantapi.model.Order;
import com.example.restaurantapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderResponse save(OrderRequest orderRequest) {
        simulateError();
        Order order = new Order();
        order.setTxId(orderRequest.getTxId());
        order.setAmount(orderRequest.getAmount());
        Order savedOrder = orderRepository.save(order);
        return new OrderResponse(savedOrder.getId());
    }

    @Transactional
    public void rollbackToSave(String txId) {
        orderRepository.deleteByTxId(txId);
    }

    private void simulateError() {
        if (Math.random() < 0.1) {
            throw new RuntimeException("An unexpected error has occurred!");
        }
    }
}
