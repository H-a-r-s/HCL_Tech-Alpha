package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "cart_items")

@Setter
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long productId;

    private String productName;

    private Double priceSnapshot;

    private Integer quantity;

    private LocalDateTime addedAt;

    // getters and setters
}