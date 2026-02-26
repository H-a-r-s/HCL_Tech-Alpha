package com.example.order_service.controller;

import com.example.order_service.dto.PlaceOrderRequest;
import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order place(@Valid @RequestBody PlaceOrderRequest request) {
        return service.placeOrder(request);
    }

    @GetMapping("/user/{userId}")
    public List<Order> byUser(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }
}