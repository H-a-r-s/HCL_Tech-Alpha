package com.example.product_service.controller;
import com.example.product_service.dto.*;
import com.example.product_service.service.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminCatalogController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public AdminCatalogController(BrandService brandService, CategoryService categoryService, ProductService productService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping("/brands")
    public BrandResponse createBrand(@Valid @RequestBody BrandRequest req) {
        return brandService.create(req);
    }

    @PostMapping("/categories")
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest req) {
        return categoryService.create(req);
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest req) {
        return productService.create(req);
    }

    @PutMapping("/products/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest req) {
        return productService.update(id, req);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
