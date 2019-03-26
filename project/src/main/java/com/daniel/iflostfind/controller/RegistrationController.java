package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.dto.UserDto;
import com.daniel.iflostfind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    static final String CONTROLLER_PATH = "/register";
    static final String USER_MODEL_NAME = "user";

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = CONTROLLER_PATH)
    public String getPage(Model model) {

        model.addAttribute(USER_MODEL_NAME, new UserDto());
        return "login";
    }

    @PostMapping(path = CONTROLLER_PATH)
    public ModelAndView register(@ModelAttribute(USER_MODEL_NAME) @Valid UserDto userDto) {

        //TODO add binding result
        userService.registerNewUserAccount(userDto);
        return new ModelAndView("login", USER_MODEL_NAME, userDto);
    }
}
