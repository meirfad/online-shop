package com.example.demoshop.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long id;
    private ItemType itemType;
    private Category category;

    public Item(ItemType itemType, Category category) {
        this.itemType = itemType;
        this.category = category;
    }

    public void setItemType(ItemType itemType) {
        if (itemType.getCategory() != this.category) {
            throw new IllegalArgumentException("Invalid item type for category");
        }
        this.itemType = itemType;
    }
}