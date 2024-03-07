package com.example.demoshop.user;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(unique=true)
    private String email;
    @Embedded
    private PersonalInfo personalInfo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "shopping_list", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "product_id")
    private List<Long> shoppingList = new ArrayList<>();

    public User(PersonalInfo personalInfo, String email) {
        this.personalInfo = personalInfo;
        this.email = email;
    }

    public void addToShoppingList(Long productId) {
        shoppingList.add(productId);
    }
}
