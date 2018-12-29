package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.annotation.NotAccessibleIfAuthenticated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NotAccessibleIfAuthenticated
public class LoginController {

    @GetMapping(path = "/login")
    public String toLoginPage() {
        return "login";
    }
}
