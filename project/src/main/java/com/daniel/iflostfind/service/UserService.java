package com.daniel.iflostfind.service;

import com.daniel.iflostfind.service.dto.UserDto;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.service.exception.UserAlreadyExistsException;

public interface UserService {
    User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistsException;
}
