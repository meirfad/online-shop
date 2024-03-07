package com.example.demoshop.purchase;

import com.example.demoshop.product.Product;
import com.example.demoshop.product.ProductService;
import com.example.demoshop.user.User;
import com.example.demoshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, ProductService productService, UserService userService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<PurchaseDTO> getPurchases() {
        return purchaseService.getPurchases();
    }

    @GetMapping(path = "{purchaseId}")
    public PurchaseDTO getPurchase(
            @PathVariable("purchaseId") Long id) {
        return purchaseService.getPurchase(id);
    }

    @PostMapping
    public void addPurchase(@RequestBody PurchaseRequest purchaseRequest) {
        purchaseService.addPurchase(purchaseRequest);
    }

    @DeleteMapping(path = "{purchaseId}")
    public void deletePurchase(
            @PathVariable("purchaseId") Long id) {
        purchaseService.deletePurchase(id);
    }

//    @PutMapping(path = "{purchaseId}")
//    public void updateProduct(
//            @PathVariable("purchaseId") Long id,
//            @RequestBody (required = false) Purchase purchase){
//        purchaseService.update(id, purchase);
//    }

    @GetMapping(path = "{purchaseId}/user")
    public User getPurchaseUser(
           @PathVariable("purchaseId") Long purchaseId ) {
        return purchaseService.getPurchaseUser(purchaseId);
    }

    @GetMapping(path = "{purchaseId}/product")
    public Product getPurchaseProduct(
            @PathVariable("purchaseId") Long purchaseId ) {
        return purchaseService.getPurchaseProduct(purchaseId);
    }


}
