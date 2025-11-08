package com.example.Felesys_mini.DTO;

import java.util.List;

public record ProductCategoryResponse(String categoryId, String categoryName, List<CategoryProductDto> products) {
}
