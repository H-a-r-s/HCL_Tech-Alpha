package com.example.notification.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEmailRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "Order ID is required")
    private String orderId;

    @NotEmpty(message = "Order items cannot be empty")
    @Valid
    private List<OrderItemDTO> items;

    @NotNull(message = "Total amount is required")
    private Double totalAmount;
}