package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.controller.dto.UserDto;
import com.daniel.iflostfind.domain.User;
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
    public User registerNewUserAccount(UserDto userDto) {

        checkIfUserAlreadyExists(userDto);

        User user = new User();
        user.setName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDefaultLocation(userDto.getDefaultLocation());

        return userRepository.save(user);
    }

    private void checkIfUserAlreadyExists(UserDto user) {
        User found = userRepository.findUserByEmail(user.getEmail());
        if (Objects.nonNull(found)) {
            throw new UserAlreadyExistsException(
                    "There is already a user with email - " + user.getEmail() + " in the system");
        }
    }
}
