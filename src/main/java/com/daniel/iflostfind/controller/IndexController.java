package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.domain.FindingGroup;
import com.daniel.iflostfind.service.GoogleMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final GoogleMapService googleMapsService;

    @Autowired
    public IndexController(GoogleMapService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @GetMapping(path = {"/", "/index"})
    public String toIndexPage(Model m/*, @AuthenticationPrincipal PersonDetails pd*/) {

//        m.addAttribute("user_default_location", pd.getDefaultLocation());
        m.addAttribute("google_map_key", googleMapsService.getMapKey());
        m.addAttribute("loss_types", FindingGroup.values());
        return "index";
    }
}
