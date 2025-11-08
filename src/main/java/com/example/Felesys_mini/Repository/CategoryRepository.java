package com.example.Felesys_mini.Repository;

import com.example.Felesys_mini.Entity.Category;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<Category> categoryTable;

    @PostConstruct
    public void init(){
        categoryTable = dynamoDbEnhancedClient.table("Category", TableSchema.fromBean(Category.class));
    }

    public Category findById(String categoryId){
        Key key = Key.builder()
                .partitionValue(categoryId)
                .build();
        return categoryTable.getItem(r -> r.key(key));
    }

    public List<Category> findAll(){
        return categoryTable.scan().items().stream().toList();
    }
}
