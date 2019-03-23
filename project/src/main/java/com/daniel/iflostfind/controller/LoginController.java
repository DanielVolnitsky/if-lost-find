package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.controller.dto.UserDto;
import com.daniel.iflostfind.service.HiddenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    static final String LOGIN_PATH = "/login";

    private final HiddenInfoService hiddenInfoService;

    @Autowired
    public LoginController(HiddenInfoService hiddenInfoService) {
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(LOGIN_PATH)
    public String toLoginPage(Model m) {

        m.addAttribute("google_map_key", hiddenInfoService.getMapKey());
        m.addAttribute(SignUpController.USER_MODEL_NAME, new UserDto());
        return "login";
    }
}
