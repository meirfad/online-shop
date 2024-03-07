package com.example.demoshop.product;

public enum ItemType {
    // Electronics
    PHONE(Category.ELECTRONICS),
    LAPTOP(Category.ELECTRONICS),
    HEADPHONES(Category.ELECTRONICS),

    // Jewelry
    RING(Category.JEWELRY),
    NECKLACE(Category.JEWELRY),
    BRACELET(Category.JEWELRY),

    // Sports
    BASKETBALL(Category.SPORTS),
    FOOTBALL(Category.SPORTS),
    TENNIS_RACKET(Category.SPORTS);

    private final Category category;

    ItemType(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }
}
