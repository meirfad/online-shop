package com.example.demoshop.product;

import com.example.demoshop.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product " + id + " does not exists."));
    }

    public void addProduct(Product product) {
//        Optional<Product> optionalProduct = productRepository.findById(product.getId());
//
//        if(optionalProduct.isPresent()) {
//            throw new IllegalStateException("Name is already taken.");
//        }
        if(product.getItem().getCategory() != product.getItem().getItemType().getCategory()) {
            throw new IllegalStateException("Product's item type doesn't match the category.");
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);

        if(!exists) {
            throw new ProductNotFoundException("Product with id " + id + ", does not exist.");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product " + id + " does not exists."));
        String name = updatedProduct.getName();
        String description = updatedProduct.getDescription();
        Double price = updatedProduct.getPrice();
        Item item = updatedProduct.getItem();

        if(name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(product.getDescription(), description)) {
            product.setDescription(description);
        }

        if(price > 0 && !Objects.equals(product.getPrice(), price)) {
            product.setPrice(price);
        }

        if(item != null && !Objects.equals(product.getItem(), item)) {
            product.setItem(item);
        }
    }

    public List<Product> findProductsByCategory(Category category) {
        return productRepository.findByItem_Category(category);
    }

    public List<Product> findProductsByItemType(ItemType itemType) {
        return productRepository.findByItem_ItemType(itemType);
    }
}
