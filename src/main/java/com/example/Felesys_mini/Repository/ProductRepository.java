package com.example.Felesys_mini.Repository;

import com.example.Felesys_mini.DTO.ProductCategoryResponse;
import com.example.Felesys_mini.Entity.Product;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<Product> productTable;


    @PostConstruct
    public void init(){
        productTable = dynamoDbEnhancedClient.table("Product", TableSchema.fromBean(Product.class));

        try{
            productTable.describeTable();
        }catch(ResourceNotFoundException e){
            productTable.createTable();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productTable.scan().items().forEach(products::add);
        return products;
    }

    public Product save(Product product){
        productTable.putItem(product);
        return product;
    }

    public Product findById(String id){
        return productTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public List<Product> findProductsByCategoryId(String categoryId) {
        return productTable.scan().items().stream()
                .filter(e -> e.getCategoryId().equals(categoryId))
                .toList();
    }
}
