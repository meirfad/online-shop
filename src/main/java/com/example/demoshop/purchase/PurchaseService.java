package com.example.demoshop.purchase;

import com.example.demoshop.exception.ProductNotFoundException;
import com.example.demoshop.exception.PurchaseNotFoundException;
import com.example.demoshop.product.Product;
import com.example.demoshop.product.ProductDTO;
import com.example.demoshop.product.ProductService;
import com.example.demoshop.user.User;
import com.example.demoshop.user.UserDTO;
import com.example.demoshop.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, ProductService productService, UserService userService) {
        this.purchaseRepository = purchaseRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Transactional
    public List<PurchaseDTO> getPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PurchaseDTO getPurchase(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new PurchaseNotFoundException("Purchase with id " + purchaseId + " doesn't exist." ));
        return mapToDTO(purchase);
    }

    public Purchase addPurchase(PurchaseRequest purchaseRequest) {
        User user = userService.getUserById(purchaseRequest.getUserId());
        Product product = productService.getProduct(purchaseRequest.getProductId());
        Purchase purchase = new Purchase(purchaseRequest.getUserId(), purchaseRequest.getProductId());
        return purchaseRepository.save(purchase);
    }

    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    public User getPurchaseUser(Long purchaseId) {
        Purchase purchase = purchaseRepository.
                findById(purchaseId).orElseThrow(() ->  new PurchaseNotFoundException("Purchase with id: " + purchaseId + " doesn't exist."));

        return purchase.getUser();
    }

    public Product getPurchaseProduct(Long purchaseId) {
        Purchase purchase = purchaseRepository.
                findById(purchaseId).orElseThrow(() ->  new PurchaseNotFoundException("Purchase with id: " + purchaseId + " doesn't exist."));

        return purchase.getProduct();
    }


    //Auxiliary
    private PurchaseDTO mapToDTO(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setId(purchase.getId());

        UserDTO userDto = new UserDTO();
        userDto.setId(purchase.getUser().getId());
        userDto.setEmail(purchase.getUser().getEmail());
        // set other fields
        dto.setUser(userDto);

        ProductDTO productDto = new ProductDTO();
        productDto.setId(purchase.getProduct().getId());
        productDto.setName(purchase.getProduct().getName());
        productDto.setPrice(purchase.getProduct().getPrice());
        // set other fields
        dto.setProduct(productDto);

        return dto;
    }

}
