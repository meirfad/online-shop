package com.example.demoshop.purchase;

import com.example.demoshop.product.ProductDTO;
import com.example.demoshop.user.UserDTO;
import lombok.Data;

@Data
public class PurchaseDTO {
    private Long id;
    private UserDTO user;
    private ProductDTO product;
}