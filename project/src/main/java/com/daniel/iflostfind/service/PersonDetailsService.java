package com.daniel.iflostfind.service;

import com.daniel.iflostfind.configuration.security.PersonDetails;
import com.daniel.iflostfind.domain.Person;
import com.daniel.iflostfind.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String u) throws UsernameNotFoundException {
        Person p = personRepository.findUserByLogin(u);
        if (Objects.isNull(p)) {
            throw new UsernameNotFoundException("no username found with login " + u);
        }
        return new PersonDetails(p);
    }
}
