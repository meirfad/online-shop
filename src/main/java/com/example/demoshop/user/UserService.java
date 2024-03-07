package com.example.demoshop.user;
import com.example.demoshop.product.Product;
import com.example.demoshop.product.ProductRepository;
import com.example.demoshop.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductService productService,
                       ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + " doesn't exist"));
        return user;
    }

    public List<Product> getShoppingList(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getShoppingList().stream()
                .map(productId -> productService.getProduct(productId)).collect(Collectors.toList());
    }

    public User addUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email already exists");
        }
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("A user with this email doesn't exist."));
        userRepository.delete(user);
    }

    @Transactional
    public void updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Product with id " + userId + " doesn't exist"));
        String email = updatedUser.getEmail();
        List<Long> updatedShoppingList = updatedUser.getShoppingList();

        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            user.setEmail(email);
        }

        user.setShoppingList(updatedShoppingList);
    }

    public User addProductToShoppingList(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getShoppingList().add(productId);
        return userRepository.save(user);
    }

}
