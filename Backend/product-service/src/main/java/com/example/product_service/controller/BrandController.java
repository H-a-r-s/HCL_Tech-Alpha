package com.example.product_service.controller;

import com.example.product_service.dto.BrandResponse;
import com.example.product_service.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public List<BrandResponse> all() {
        return service.getAll();
    }
}