package com.daniel.iflostfind.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = "/login")
    public String toLoginPage() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (!(a instanceof AnonymousAuthenticationToken)) {
            return "redirect:/index";
        }
        return "login";
    }
}
