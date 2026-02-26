package com.example.order_service.client;

import com.example.order_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "productClient", url = "${product.service.url}")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductById(@PathVariable("id") Long id);
}