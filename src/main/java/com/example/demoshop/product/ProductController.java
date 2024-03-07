package com.example.demoshop.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/categories/{category}")
    public List<Product> getProductsByCategory(
            @PathVariable("category") Category category) {
        return productService.findProductsByCategory(category);
    }

    @GetMapping(path = "/items/{itemType}")
    public List<Product> getProductsByItemType(
            @PathVariable("itemType") ItemType itemType) {
        return productService.findProductsByItemType(itemType);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(
            @PathVariable("productId") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(
            @PathVariable("productId") Long id,
            @RequestBody (required = false) Product product){
        productService.updateProduct(id, product);
    }

}
