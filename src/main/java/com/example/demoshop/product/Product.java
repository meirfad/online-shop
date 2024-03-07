package com.example.demoshop.product;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private Double price;
    private String name;
    private String description;
    @ManyToOne(cascade=CascadeType.ALL)
    private Item item;

    public Product(Double price, String name, String description, Item item) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.item = item;
    }
}
