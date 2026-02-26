package com.example.product_service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brands")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String name;
}