package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8082")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable Long id);

    class ProductDTO {
        public Long id;
        public String name;
        public Double price;
        public Integer stock;
    }
}