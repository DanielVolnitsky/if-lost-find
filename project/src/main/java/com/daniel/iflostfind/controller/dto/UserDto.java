package com.daniel.iflostfind.controller.dto;

import com.daniel.iflostfind.util.annotation.PasswordMatches;
import com.daniel.iflostfind.util.annotation.ValidEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String firstName;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String lastName;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String password;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    @Getter
    @Setter
    private String email;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String country;

    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String city;
}
