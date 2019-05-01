package com.daniel.iflostfind.service.converter.impl;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.service.converter.EntityToDtoConverter;
import com.daniel.iflostfind.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements EntityToDtoConverter<User, UserDto> {

    @Override
    public UserDto convertEntityToDto(User user) {
        UserDto dto = new UserDto();

        dto.setFirstName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setDefaultLocation(user.getDefaultLocation());

        return dto;
    }
}
