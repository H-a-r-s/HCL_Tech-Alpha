package com.example.inventory_service.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "inventory")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @Column(name = "product_id")
    private Long productId;   // same id as product-service product

    @Column(nullable = false)
    private Integer stockQty;

    @Column(nullable = false)
    private Instant updatedAt;
}