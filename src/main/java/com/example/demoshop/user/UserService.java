package com.example.demoshop.user;
import com.example.demoshop.exception.EmailAlreadyExistsException;
import com.example.demoshop.exception.UserNotFoundException;
import com.example.demoshop.product.Product;
import com.example.demoshop.product.ProductRepository;
import com.example.demoshop.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " doesn't exist"));
        return user;
    }

    public List<Product> getShoppingList(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " doesn't exist"));
        return user.getShoppingList().stream()
                .map(productId -> productService.getProduct(productId)).collect(Collectors.toList());
    }

    public User addUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("A user with the same email already exists");
        }

        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailAlreadyExistsException("A user with this email doesn't exist."));
        userRepository.delete(user);
    }

    @Transactional
    public void updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " doesn't exist"));
        String email = updatedUser.getEmail();
        List<Long> updatedShoppingList = updatedUser.getShoppingList();

        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            user.setEmail(email);
        }

        user.setShoppingList(updatedShoppingList);
    }

    public User addProductToShoppingList(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id" + userId + " doesn't exist"));
        user.getShoppingList().add(productId);
        return userRepository.save(user);
    }

}
