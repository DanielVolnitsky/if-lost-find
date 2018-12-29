package com.daniel.iflostfind;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IfLostFindApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public IfLostFindApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(IfLostFindApplication.class, args);
    }

    @Override
    public void run(String... args) {

        User dan = User.builder()
            .name("d").lastName("v")
            .email("d@mail.com").password("d")
            .country("c").city("c")
            .build();

        userRepository.save(dan);

        User kir = User.builder()
            .name("k").lastName("a")
            .email("test@test.com").password("Qwerty1234567")
            .country("c").city("c")
            .build();

        userRepository.save(kir);
    }
}
