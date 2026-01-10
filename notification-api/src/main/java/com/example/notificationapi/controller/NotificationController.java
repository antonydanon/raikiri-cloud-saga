package com.example.notificationapi.controller;

import com.example.notificationapi.dto.NotificationRequest;
import com.example.notificationapi.dto.NotificationResponse;
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
    public ResponseEntity<NotificationResponse> save(@RequestBody NotificationRequest notificationRequest) {
        return ResponseEntity.ok(notificationService.save(notificationRequest));
    }

    @DeleteMapping("/rollback/{txId}")
    public ResponseEntity<String> rollbackToSave(@PathVariable String txId) {
        notificationService.rollbackToSave(txId);
        return ResponseEntity.ok().body(txId + " transaction has been successfully cancelled.");
    }
}
