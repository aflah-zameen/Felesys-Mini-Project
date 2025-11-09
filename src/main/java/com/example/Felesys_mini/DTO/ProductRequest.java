package com.example.Felesys_mini.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "Product name is required")
        String productName,

        @Positive(message = "Price must be greater than zero")
        double price,

        @NotBlank(message = "Product image URL is required")
        String productImage,

        @NotBlank(message = "Brand is required")
        String brand,

        @NotBlank(message = "Category Name is required")
        String categoryName
){}
