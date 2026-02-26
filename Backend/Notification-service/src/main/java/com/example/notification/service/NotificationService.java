package com.example.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.notification.dto.OrderEmailRequest;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    /**
     * Handles order confirmation notification logic
     */
    public void sendOrderConfirmation(OrderEmailRequest request) {

        // Business logic can go here later
        // Example: logging, formatting, conditional sending, etc.

        emailService.sendOrderEmail(request);
    }
}