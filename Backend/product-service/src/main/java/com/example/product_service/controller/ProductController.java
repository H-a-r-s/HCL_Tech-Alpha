package com.example.product_service.controller;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponse> browse(
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId
    ) {
        return service.browse(brandId, categoryId);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return service.getById(id);
    }
}