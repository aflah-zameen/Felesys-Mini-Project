package com.example.Felesys_mini.Service;

import com.example.Felesys_mini.DTO.CategoryProductDto;
import com.example.Felesys_mini.DTO.ProductCategoryResponse;
import com.example.Felesys_mini.Entity.Category;
import com.example.Felesys_mini.Entity.Product;
import com.example.Felesys_mini.Repository.CategoryRepository;
import com.example.Felesys_mini.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getProducts(){
        return productRepository.getAllProducts();
    }

    public void save(Product product) {
        if(product.getProductId() == null){
            product.setProductId("PROD_"+ UUID.randomUUID());
        }
        productRepository.save(product);
    }

    public ProductCategoryResponse findProductsByCategoryId(String categoryId) {
        List<Product> products = productRepository.findProductsByCategoryId(categoryId);
        Category category = categoryRepository.findById(categoryId);
        return new ProductCategoryResponse(
                categoryId,
                category.getCategoryName(),
                products.stream()
                        .map(el -> new CategoryProductDto(el.getProductId(),el.getProductName(),el.getPrice(),el.getBrand(),el.getProductImage()))
                        .toList()
        );
    }
}
