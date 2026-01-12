package com.example.notificationapi.controller;

import com.example.notificationapi.dto.NotificationSaveDtoRequest;
import com.example.notificationapi.dto.NotificationSaveDtoResponse;
import com.example.notificationapi.model.Notification;
import com.example.notificationapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public NotificationSaveDtoResponse save(@RequestBody NotificationSaveDtoRequest request) {
        Notification notification = notificationService.save(request.txId(), request.message());
        return new NotificationSaveDtoResponse(notification.getId());
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        notificationService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
