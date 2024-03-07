package com.example.demoshop.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class PersonalInfo {
    private String name;
    private String address;
    private String paymentMethod;
}
