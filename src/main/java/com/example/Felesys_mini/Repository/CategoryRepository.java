package com.example.Felesys_mini.Repository;

import com.example.Felesys_mini.Entity.Category;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public boolean isAvailable(String name) {
         List<Category> categories = categoryTable.scan().items().stream().toList();
         for(var el: categories ){
             if(el.getCategoryName().equals(name)){
                 return true;
             }
         }
         return false;
    }

    public Category addCategory(Category category) {
        categoryTable.putItem(category);
        return category;
    }

    public Category getCategory(@NotBlank(message = "Category Name is required") String s) {
        Optional<Category> category = categoryTable.scan().items().stream().filter(r -> r.getCategoryName().equals(s)).findFirst();
        if(!category.isPresent()){
           return addCategory(Category.builder()
                    .categoryId("Cat_"+ UUID.randomUUID())
                    .categoryName(s)
                    .build());
        }
        return category.orElse(null);
    }
}
