package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.configuration.security.PersonDetails;
import com.daniel.iflostfind.service.HiddenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    static final String INDEX_PATH = "/index";

    private final HiddenInfoService hiddenInfoService;

    @Autowired
    public IndexController(HiddenInfoService hiddenInfoService) {
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(path = {"/", INDEX_PATH})
    public String toIndexPage(Model m, @AuthenticationPrincipal PersonDetails pd) {

        m.addAttribute("user_default_location", pd.getDefaultLocation());
        m.addAttribute("google_map_key", hiddenInfoService.getMapKey());
        return "index";
    }
}

