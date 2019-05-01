package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.dto.UserDto;
import com.daniel.iflostfind.service.GoogleMapKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final GoogleMapKeyService googleMapKeyService;

    @Autowired
    public LoginController(GoogleMapKeyService googleMapKeyService) {
        this.googleMapKeyService = googleMapKeyService;
    }

    @GetMapping("/login")
    public String toLoginPage(Model m) {

        m.addAttribute("google_map_key", googleMapKeyService.getMapKey());
        m.addAttribute(RegistrationController.USER_MODEL_NAME, new UserDto());
        return "login";
    }
}
