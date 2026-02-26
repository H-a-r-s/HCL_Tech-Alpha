package com.example.product_service.repository;


import com.example.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsActiveTrue();

    List<Product> findByCategory_IdAndIsActiveTrue(Long categoryId);

    List<Product> findByBrand_IdAndIsActiveTrue(Long brandId);

    List<Product> findByBrand_IdAndCategory_IdAndIsActiveTrue(Long brandId, Long categoryId);
}
