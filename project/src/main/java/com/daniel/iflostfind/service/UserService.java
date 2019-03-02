package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.controller.dto.UserDto;

public interface UserService {
    User registerNewUserAccount(UserDto userDto);
}
