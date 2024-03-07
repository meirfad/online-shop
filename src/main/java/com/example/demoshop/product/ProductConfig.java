package com.example.demoshop.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Item item1 = new Item(ItemType.BASKETBALL, Category.SPORTS);
            Item item2 = new Item(ItemType.BRACELET, Category.JEWELRY);
            Product dummy1 = new Product(15.5, "NBA Standard Basketball", "Official Ball", item1);
            Product dummy2 =new Product(120.0, "Diamond Bracelet ", "24K Bracelet", item2);

            productRepository.saveAll(List.of(dummy1, dummy2));
        };
    }
}
