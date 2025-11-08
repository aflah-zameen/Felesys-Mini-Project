package com.example.Felesys_mini.Controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductController {

    @Value("${name}")
    String name;

    @Value("${email}")
    String password;

    @PostConstruct
    public void init(){
        System.out.println(name+" "+password);
    }
}
