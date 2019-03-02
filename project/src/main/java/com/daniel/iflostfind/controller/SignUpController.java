package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.controller.dto.UserDto;
import com.daniel.iflostfind.service.UserService;
import com.daniel.iflostfind.util.annotation.NotAccessibleIfAuthenticated;
import com.daniel.iflostfind.util.exception.UserAlreadyExistsException;
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

    static final String SIGN_UP_PATH = "/signup";
    static final String SIGN_UP_PAGE = "signup";
    static final String USER_MODEL_NAME = "user";

    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = SIGN_UP_PATH)
    public String toSignUpPage(Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute(USER_MODEL_NAME, userDto);
        return SIGN_UP_PAGE;
    }

    @PostMapping(path = SIGN_UP_PATH)
    public ModelAndView signUp(@ModelAttribute(USER_MODEL_NAME) @Valid UserDto userDto, BindingResult bindingResult) {

        User registeredUser = null;

        if (!bindingResult.hasErrors()) {
            registeredUser = registerNewUserAccount(userDto);
        }

        if (Objects.isNull(registeredUser)) {
            bindingResult.rejectValue("email", "email.invalid", "user with this email already exists");
        }

        String viewName = bindingResult.hasErrors() ? SIGN_UP_PAGE : LoginController.LOGIN_PAGE;
        return new ModelAndView(viewName, USER_MODEL_NAME, userDto);
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
