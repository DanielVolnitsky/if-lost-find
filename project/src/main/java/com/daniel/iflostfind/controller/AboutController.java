package com.daniel.iflostfind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    final String ABOUT_PATH = "/about";
    final String ABOUT_PAGE = "about";

    @GetMapping(path = ABOUT_PATH)
    public String toAboutPage() { return ABOUT_PAGE; }

}