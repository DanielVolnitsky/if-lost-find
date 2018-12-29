package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.annotation.NotAccessibleIfAuthenticated;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.dto.UserDto;
import com.daniel.iflostfind.exception.UserAlreadyExistsException;
import com.daniel.iflostfind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@NotAccessibleIfAuthenticated
public class SignUpController {

    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/signup")
    public String toSignUpPage(Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "signup";
    }


    //TODO add passwords matching check
    @PostMapping(path = "/signup")
    public ModelAndView signUp(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult) {

        User registeredUser = null;

        if (!bindingResult.hasErrors()) {
            registeredUser = registerNewUserAccount(userDto);
        }

        if (Objects.isNull(registeredUser)) {
            bindingResult.rejectValue("email", "user with this email already exists");
        }

        String viewName = bindingResult.hasErrors() ? "signup" : "login";
        return new ModelAndView(viewName, "user", userDto);
    }

    private User registerNewUserAccount(UserDto userDto) {

        User registered = null;

        try {
            registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistsException e) {
            //TODO log
        }

        return registered;
    }
}
