package com.example.product_service.service;
import com.example.product_service.dto.CategoryRequest;
import com.example.product_service.dto.CategoryResponse;
import com.example.product_service.entity.Category;
import com.example.product_service.exception.NotFoundException;
import com.example.product_service.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<CategoryResponse> getAll() {
        return repo.findAll().stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName()))
                .toList();
    }

    public Category getEntity(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Category not found: " + id));
    }

    public CategoryResponse create(CategoryRequest req) {
        String name = req.getName().trim();
        Category c = new Category();
        c.setName(name);
        Category saved = repo.save(c);
        return new CategoryResponse(saved.getId(), saved.getName());
    }
}