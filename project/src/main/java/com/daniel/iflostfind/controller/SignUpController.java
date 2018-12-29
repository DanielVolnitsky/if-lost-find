package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.dto.UserDto;
import com.daniel.iflostfind.exception.UserAlreadyExistsExeption;
import com.daniel.iflostfind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class SignUpController {

    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/signup")
    public String toSignUpPage(Model model) {

        //TODO make aop
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (!(a instanceof AnonymousAuthenticationToken)) {
            return "redirect:/index";
        }

        model.addAttribute("user", new UserDto());
        return "signup";
    }

    //TODO add email check
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

        //TODO discover how to display errors in html
        if (bindingResult.hasErrors()) {
            return new ModelAndView("signup", "user", userDto);
        } else {
            return new ModelAndView("login", "user", userDto);
        }
    }

    private User registerNewUserAccount(UserDto userDto) {

        User registered = null;

        try {
            registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistsExeption e) {
            //TODO log
        }

        return registered;
    }
}
