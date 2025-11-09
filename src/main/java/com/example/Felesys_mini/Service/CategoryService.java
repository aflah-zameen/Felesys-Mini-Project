package com.example.Felesys_mini.Service;

import com.example.Felesys_mini.DTO.CategoryResponse;
import com.example.Felesys_mini.Entity.Category;
import com.example.Felesys_mini.Repository.CategoryRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return new CategoryResponse(
                categories.size(),
                categories
        );
    }

    public Category addCategory(Category category){
        return categoryRepository.addCategory(category);
    }

    public boolean isAvailable(String name){
       return categoryRepository.isAvailable(name);
    }

    public Category getCategory(@NotBlank(message = "Category Name is required") String s) {
        return categoryRepository.getCategory(s);
    }
}
