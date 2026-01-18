package com.example.notificationapi.service;

import com.example.notificationapi.model.Notification;
import com.example.notificationapi.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.notificationapi.configuration.KafkaConfiguration.ORDER_TOPIC;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final KafkaProducerService kafkaProducerService;
    private final NotificationRepository notificationRepository;

    public Notification save(String txId, String message) {
        Notification notification = new Notification();
        notification.setTxId(txId);
        notification.setMessage(message);
        Notification savedNotification = notificationRepository.save(notification);
        kafkaProducerService.sendMessage(ORDER_TOPIC, message);
        return savedNotification;
    }

    @Transactional
    public void rollbackToSave(String txId) {
        notificationRepository.deleteByTxId(txId);
    }
}