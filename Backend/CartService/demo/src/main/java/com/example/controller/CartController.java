package com.example.controller;

import com.example.dto.CartRequest;
import com.example.dto.CartResponse;
import com.example.entity.CartItem;
import org.springframework.web.bind.annotation.*;
import com.example.service.CartService;

@RestController
@RequestMapping("/cart")
public class
CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }

    @GetMapping("/{userId}")
    public CartResponse getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/remove/{id}")
    public String removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return "Item removed";
    }

    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared";
    }
}