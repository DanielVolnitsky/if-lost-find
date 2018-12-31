package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.util.annotation.NotAccessibleIfAuthenticated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NotAccessibleIfAuthenticated
public class LoginController {

    static final String LOGIN_PATH = "/login";
    static final String LOGIN_PAGE = "login";

    @GetMapping(path = LOGIN_PATH)
    public String toLoginPage() {
        return LOGIN_PAGE;
    }
}
