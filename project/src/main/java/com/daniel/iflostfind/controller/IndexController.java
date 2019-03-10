package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.HiddenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final HiddenInfoService hiddenInfoService;

    static final String INDEX_PATH = "/index";
    static final String INDEX_PAGE = "index";

    @Autowired
    public IndexController(HiddenInfoService hiddenInfoService) {
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(path = INDEX_PATH)
    public String toIndexPage(Model m) {

        m.addAttribute("google_map_key", hiddenInfoService.getMapKey());
        return INDEX_PAGE;
    }
}

