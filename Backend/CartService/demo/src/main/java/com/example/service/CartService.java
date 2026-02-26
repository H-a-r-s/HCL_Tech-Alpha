package com.example.service;

import com.example.client.ProductClient;
import com.example.dto.CartRequest;
import com.example.dto.CartResponse;
import com.example.entity.CartItem;
import com.example.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public CartService(CartRepository cartRepository,
                       ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.productClient = productClient;
    }

    public CartItem addToCart(CartRequest request) {

        ProductClient.ProductDTO product =
                productClient.getProductById(request.getProductId());

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        CartItem item = new CartItem();
        item.setUserId(request.getUserId());
        item.setProductId(product.id);
        item.setProductName(product.name);
        item.setPriceSnapshot(product.price);
        item.setQuantity(request.getQuantity());
        item.setAddedAt(LocalDateTime.now());

        return cartRepository.save(item);
    }

    public CartResponse getCart(Long userId) {

        List<CartItem> items = cartRepository.findByUserId(userId);

        double total = items.stream()
                .mapToDouble(i -> i.getPriceSnapshot() * i.getQuantity())
                .sum();

        CartResponse response = new CartResponse();
        response.setUserId(userId);
        response.setItems(items);
        response.setTotalAmount(total);

        return response;
    }

    public void removeItem(Long id) {
        cartRepository.deleteById(id);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}