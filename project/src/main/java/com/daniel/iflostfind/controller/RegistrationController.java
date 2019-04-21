package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.GoogleMapService;
import com.daniel.iflostfind.service.UserService;
import com.daniel.iflostfind.service.dto.UserDto;
import com.daniel.iflostfind.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    static final String USER_MODEL_NAME = "user";

    private final GoogleMapService googleMapService;
    private final UserService userService;

    @Autowired
    public RegistrationController(GoogleMapService googleMapService, UserService userService) {
        this.googleMapService = googleMapService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getPage(Model m) {

        m.addAttribute("google_map_key", googleMapService.getMapKey());
        m.addAttribute(USER_MODEL_NAME, new UserDto());
        return "registration";
    }

    @PostMapping("/register")
    public ModelAndView register(
            @ModelAttribute(USER_MODEL_NAME) @Valid UserDto userDto,
            BindingResult br) {

        if(br.hasErrors()){
            return new ModelAndView("registration", USER_MODEL_NAME, userDto);
        }

        try {
            userService.registerNewUserAccount(userDto);
            return new ModelAndView("login", USER_MODEL_NAME, userDto);
        } catch (UserAlreadyExistsException e) {
            FieldError fe = new FieldError(
                    "email", "email", "User with this email already exists");
            br.addError(fe);
        }

        return new ModelAndView("registration", USER_MODEL_NAME, userDto);
    }
}
