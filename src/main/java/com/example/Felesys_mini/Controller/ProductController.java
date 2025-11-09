package com.example.Felesys_mini.Controller;


import com.example.Felesys_mini.DTO.CategoryResponse;
import com.example.Felesys_mini.DTO.ProductCategoryResponse;
import com.example.Felesys_mini.DTO.ProductRequest;
import com.example.Felesys_mini.Entity.Category;
import com.example.Felesys_mini.Entity.Product;
import com.example.Felesys_mini.Repository.ProductRepository;
import com.example.Felesys_mini.Service.CategoryService;
import com.example.Felesys_mini.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<String> addProduct(@Valid  @RequestBody ProductRequest productRequest){

        Category category = categoryService.getCategory(productRequest.categoryName());

        Product product = Product.builder()
                .productName(productRequest.productName())
                .brand(productRequest.brand())
                .productImage(productRequest.productImage())
                .categoryId(category.getCategoryId())
                .price(productRequest.price())
                .build();
        productService.save(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponse> getCategories(){
        CategoryResponse response = categoryService.getCategories();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ProductCategoryResponse> getProductsByCategory(@RequestParam String categoryId){
        ProductCategoryResponse response = productService.findProductsByCategoryId(categoryId);
        return ResponseEntity.ok(response);
    }


}
