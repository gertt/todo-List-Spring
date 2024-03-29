package com.todolist;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

import static com.todolist.util.AppConstants.*;

@SpringBootApplication
public class ToDoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinaryConfig() {
        Map<String, String> config = new HashMap<>();
        config.put(CLOUD_NAME, cloudName);
        config.put(API_KEY, apiKey);
        config.put(API_SECRET, apiSecret);
        return new Cloudinary(config);
    }
}
