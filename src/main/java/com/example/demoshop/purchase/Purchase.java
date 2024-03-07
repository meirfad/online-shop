package com.example.demoshop.purchase;

import com.example.demoshop.product.Product;
import com.example.demoshop.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "purchases")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    // Default constructor
    public Purchase() {
    }

    // Constructor with user and product
    public Purchase(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
