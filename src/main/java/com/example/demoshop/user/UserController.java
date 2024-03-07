package com.example.demoshop.user;

import com.example.demoshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{userId}")
    public User getUser(
            @PathVariable("userId") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "{userId}/shopping-list")
    public List<Product> getShoppingList(@PathVariable("userId") Long userId) {
        return userService.getShoppingList(userId);
    }

    @PostMapping(path = "/{userId}/add-product/{productId}")
    public User addProductToShoppingList(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId) {
        return userService.addProductToShoppingList(userId, productId);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping(path = "{email}")
    public void deleteUser(@PathVariable("email") String email) {
        userService.deleteUser(email);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long id,
            @RequestBody User user) {
        userService.updateUser(id, user);
    }
}