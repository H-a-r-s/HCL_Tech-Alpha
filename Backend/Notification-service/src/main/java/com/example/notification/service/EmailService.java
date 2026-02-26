package com.example.notification.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.notification.dto.OrderEmailRequest;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    /**
     * Sends order confirmation email
     */
    @Async
    public void sendOrderEmail(OrderEmailRequest request) {

        try {

            // Prepare dynamic values for template
            Context context = new Context();
            context.setVariable("userName", request.getUserName());
            context.setVariable("orderId", request.getOrderId());
            context.setVariable("items", request.getItems());
            context.setVariable("totalAmount", request.getTotalAmount());

            // Process HTML template
            String htmlContent = templateEngine.process("order-confirmation", context);

            // Create email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(request.getEmail());
            helper.setSubject("Order Confirmation - #" + request.getOrderId());
            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}