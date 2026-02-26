package com.example.product_service.service;
import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.entity.Brand;
import com.example.product_service.entity.Category;
import com.example.product_service.entity.Product;
import com.example.product_service.exception.NotFoundException;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository repo, BrandService brandService, CategoryService categoryService) {
        this.repo = repo;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    public List<ProductResponse> browse(Long brandId, Long categoryId) {
        List<Product> list;
        if (brandId != null && categoryId != null) {
            list = repo.findByBrand_IdAndCategory_IdAndIsActiveTrue(brandId, categoryId);
        } else if (brandId != null) {
            list = repo.findByBrand_IdAndIsActiveTrue(brandId);
        } else if (categoryId != null) {
            list = repo.findByCategory_IdAndIsActiveTrue(categoryId);
        } else {
            list = repo.findByIsActiveTrue();
        }
        return list.stream().map(this::toResponse).toList();
    }

    public ProductResponse getById(Long id) {
        Product p = repo.findById(id).orElseThrow(() -> new NotFoundException("Product not found: " + id));
        return toResponse(p);
    }

    public ProductResponse create(ProductRequest req) {
        Brand brand = brandService.getEntity(req.getBrandId());
        Category category = categoryService.getEntity(req.getCategoryId());

        Product p = new Product();
        p.setBrand(brand);
        p.setCategory(category);
        p.setName(req.getName().trim());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());
        p.setActive(req.getIsActive() == null ? true : req.getIsActive());

        Product saved = repo.save(p);
        return toResponse(saved);
    }

    public ProductResponse update(Long id, ProductRequest req) {
        Product p = repo.findById(id).orElseThrow(() -> new NotFoundException("Product not found: " + id));

        if (req.getBrandId() != null) p.setBrand(brandService.getEntity(req.getBrandId()));
        if (req.getCategoryId() != null) p.setCategory(categoryService.getEntity(req.getCategoryId()));
        if (req.getName() != null && !req.getName().isBlank()) p.setName(req.getName().trim());
        if (req.getDescription() != null) p.setDescription(req.getDescription());
        if (req.getPrice() > 0) p.setPrice(req.getPrice());
        if (req.getIsActive() != null) p.setActive(req.getIsActive());

        return toResponse(repo.save(p));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Product not found: " + id);
        repo.deleteById(id);
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getBrand().getId(),
                p.getBrand().getName(),
                p.getCategory().getId(),
                p.getCategory().getName(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.isActive()
        );
    }
}