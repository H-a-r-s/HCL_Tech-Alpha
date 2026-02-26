package com.example.notification.controller;


import com.example.notification.dto.OrderEmailRequest;
import com.example.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/order-confirmation")
    public ResponseEntity<String> sendOrderConfirmation(
            @Valid @RequestBody OrderEmailRequest request) {

        notificationService.sendOrderConfirmation(request);

        return ResponseEntity.ok("Order confirmation email sent successfully.");
    }
}