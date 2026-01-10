package com.example.courierapi.service;

import com.example.courierapi.dto.DeliveryRequest;
import com.example.courierapi.dto.DeliveryResponse;
import com.example.courierapi.model.Delivery;
import com.example.courierapi.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryResponse save(DeliveryRequest deliveryRequest) {
        Delivery delivery = new Delivery();
        delivery.setTxId(deliveryRequest.getTxId());
        delivery.setAddress(deliveryRequest.getAddress());
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return new DeliveryResponse(savedDelivery.getId());
    }

    @Transactional
    public void rollbackToSave(String txId) {
        deliveryRepository.deleteByTxId(txId);
    }
}