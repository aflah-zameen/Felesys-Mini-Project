package com.example.Felesys_mini.DTO;

public record CategoryProductDto(
        String productId,
        String productName,
        double price,
        String brand,
        String productImage
) {
}
