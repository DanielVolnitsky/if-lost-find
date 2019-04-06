package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.configuration.security.PersonDetails;
import com.daniel.iflostfind.service.GoogleMapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final GoogleMapApiService googleMapsService;

    @Autowired
    public IndexController(GoogleMapApiService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @GetMapping(path = {"/", "/index"})
    public String toIndexPage(Model m/*, @AuthenticationPrincipal PersonDetails pd*/) {

//        m.addAttribute("user_default_location", pd.getDefaultLocation());
        m.addAttribute("google_map_key", googleMapsService.getMapKey());
        return "index";
    }
}

