package com.example.product_service.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotNull(message = "brandId is required")
    private Long brandId;

    @NotNull(message = "categoryId is required")
    private Long categoryId;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    private Boolean isActive; // optional

}
