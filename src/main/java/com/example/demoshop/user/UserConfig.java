package com.example.demoshop.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner2(UserRepository userRepository) {
        return args -> {
            PersonalInfo pi1 = new PersonalInfo("Meir Fadida", "Ha'Hagana 6-33", "VISA");
            PersonalInfo pi2 = new PersonalInfo("Yulia Portnov", "Pines 20B", "AMERICAN EXPRESS");
            User user1 = new User(pi1, "email@1.com");
            User user2 = new User(pi2, "email@2.com");
            userRepository.saveAll(List.of(user1, user2));
        };
    }
}
