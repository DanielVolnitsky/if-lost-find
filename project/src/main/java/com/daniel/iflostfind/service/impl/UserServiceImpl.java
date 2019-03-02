package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.controller.dto.UserDto;
import com.daniel.iflostfind.repository.UserRepository;
import com.daniel.iflostfind.service.UserService;
import com.daniel.iflostfind.util.exception.UserAlreadyExistsException;
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
            throw new UserAlreadyExistsException("There is already a user with email - " + dto.getEmail() + " in the system");
        }

        User user = new User();
        user.setName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCountry(dto.getCountry());
        user.setCity(dto.getCity());

        return userRepository.save(user);
    }

    private boolean userExists(String email) {
        User user = userRepository.findUserByEmail(email);
        return Objects.nonNull(user);
    }
}
