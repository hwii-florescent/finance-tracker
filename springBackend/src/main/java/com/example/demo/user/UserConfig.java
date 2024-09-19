package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner cmdLineRunner(UserRepository repository) {
        return args -> {
            User admin = new User(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            User alex = new User(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 5)
            );

            Optional<User> userAdmin = repository.findUserByEmail(admin.getEmail());
            Optional<User> userAlex = repository.findUserByEmail(alex.getEmail());

            if (userAdmin.isEmpty() && userAlex.isEmpty()) {
                repository.saveAll(List.of(admin, alex));
            }
        };
    }
}
