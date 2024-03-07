package com.example.demoshop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

//    @Query("SELECT p From Product p WHERE p.name =?1")
//    Optional<Product> findByName(String name);
    List<Product> findByItem_ItemType(ItemType itemType);
    List<Product> findByItem_Category(Category category);
}
