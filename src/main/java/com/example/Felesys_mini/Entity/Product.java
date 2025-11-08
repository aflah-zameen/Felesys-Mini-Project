package com.example.Felesys_mini.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String productId;
    private String productName;
    private double price;
    private String brand;
    private String productImage;
    private String categoryId;

    @DynamoDbPartitionKey
    public String getProductId(){
        return productId;
    }
}
