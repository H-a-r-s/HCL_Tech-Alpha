package com.example.product_service.service;
import com.example.product_service.dto.BrandRequest;
import com.example.product_service.dto.BrandResponse;
import com.example.product_service.entity.Brand;
import com.example.product_service.exception.NotFoundException;
import com.example.product_service.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository repo;

    public BrandService(BrandRepository repo) {
        this.repo = repo;
    }

    public List<BrandResponse> getAll() {
        return repo.findAll().stream()
                .map(b -> new BrandResponse(b.getId(), b.getName()))
                .toList();
    }

    public Brand getEntity(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Brand not found: " + id));
    }

    public BrandResponse create(BrandRequest req) {
        String name = req.getName().trim();
        Brand saved = repo.save(new Brand(null, name)); // if you don't have this constructor, use setters
        return new BrandResponse(saved.getId(), saved.getName());
    }
}