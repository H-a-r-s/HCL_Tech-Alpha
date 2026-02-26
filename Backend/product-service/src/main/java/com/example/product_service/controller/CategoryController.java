package com.example.product_service.controller;
import com.example.product_service.dto.CategoryResponse;
import com.example.product_service.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryResponse> all() {
        return service.getAll();
    }
}