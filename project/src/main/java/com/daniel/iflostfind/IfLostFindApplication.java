package com.daniel.iflostfind;

import com.daniel.iflostfind.domain.Person;
import com.daniel.iflostfind.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IfLostFindApplication implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Autowired
    public IfLostFindApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(IfLostFindApplication.class, args);
    }

    @Override
    public void run(String... args) {
        personRepository.save(new Person("k", "k"));
        personRepository.save(new Person("d", "d"));
    }
}
