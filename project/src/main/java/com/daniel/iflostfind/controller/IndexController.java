package com.daniel.iflostfind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    static final String INDEX_PATH = "/index";
    static final String INDEX_PAGE = "index";

    @GetMapping(path = INDEX_PATH)
    public String homePage() {
        return INDEX_PAGE;
    }
}

