package com.example.demoshop.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

public class PurchaseRequest {
    private Long userId;
    private Long productId;

    public PurchaseRequest(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }
}