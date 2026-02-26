package com.example.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest {
    @NotBlank(message = "Brand name is required")
    private String name;




}