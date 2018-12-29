package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.dto.UserDto;
import com.daniel.iflostfind.exception.UserAlreadyExistsExeption;
import com.daniel.iflostfind.repository.UserRepository;
import com.daniel.iflostfind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto dto) {
        if (userExists(dto.getEmail())) {
            throw new UserAlreadyExistsExeption("There is already a user with email - " + dto.getEmail() + " in the system");
        }

        User user = User.builder()
            .name(dto.getFirstName())
            .lastName(dto.getLastName())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .city(dto.getCity())
            .country(dto.getCountry())
            .build();

        return userRepository.save(user);
    }

    private boolean userExists(String email) {
        User user = userRepository.findUserByEmail(email);
        return Objects.nonNull(user);
    }
}
