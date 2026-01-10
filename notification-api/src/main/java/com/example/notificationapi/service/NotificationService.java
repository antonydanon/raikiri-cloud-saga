package com.example.notificationapi.service;

import com.example.notificationapi.dto.NotificationRequest;
import com.example.notificationapi.dto.NotificationResponse;
import com.example.notificationapi.model.Notification;
import com.example.notificationapi.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationResponse save(NotificationRequest notificationRequest) {
        Notification notification = new Notification();
        notification.setTxId(notificationRequest.getTxId());
        notification.setMessage(notificationRequest.getMessage());
        Notification savedNotification = notificationRepository.save(notification);
        return new NotificationResponse(savedNotification.getId());
    }

    @Transactional
    public void rollbackToSave(String txId) {
        notificationRepository.deleteByTxId(txId);
    }
}