package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.HiddenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    final String MAP_PAGE_PATH = "/map";
    final String MAP_PAGE = "map";

    private final HiddenInfoService hiddenInfoService;

    @Autowired
    public MapController(HiddenInfoService hiddenInfoService) {
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(path = MAP_PAGE_PATH)
    public String toMapPage(Model model) {

        String key = hiddenInfoService.getMapKey();
        model.addAttribute("google_map_key", key);
        return MAP_PAGE;
    }
}
