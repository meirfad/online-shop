package com.example.demoshop.product;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    // add other fields as needed
}
