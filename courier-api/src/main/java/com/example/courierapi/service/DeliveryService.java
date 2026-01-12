package com.example.courierapi.service;

import com.example.courierapi.model.Delivery;
import com.example.courierapi.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public Delivery save(String txId, String address) {
        Delivery delivery = new Delivery();
        delivery.setTxId(txId);
        delivery.setAddress(address);
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public void rollbackToSave(String txId) {
        deliveryRepository.deleteByTxId(txId);
    }
}