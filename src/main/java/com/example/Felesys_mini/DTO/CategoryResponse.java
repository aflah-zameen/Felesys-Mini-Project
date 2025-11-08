package com.example.Felesys_mini.DTO;

import com.example.Felesys_mini.Entity.Category;

import java.util.List;

public record CategoryResponse(long totalCategories, List<Category> categories) {
}
