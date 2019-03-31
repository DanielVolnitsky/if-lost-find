package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.dto.UserDto;
import com.daniel.iflostfind.service.GoogleMapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    static final String LOGIN_PATH = "/login";

    private final GoogleMapApiService googleMapApiService;

    @Autowired
    public LoginController(GoogleMapApiService googleMapApiService) {
        this.googleMapApiService = googleMapApiService;
    }

    @GetMapping(LOGIN_PATH)
    public String toLoginPage(Model m) {

        m.addAttribute("google_map_key", googleMapApiService.getMapKey());
        m.addAttribute(RegistrationController.USER_MODEL_NAME, new UserDto());
        return "login";
    }
}
