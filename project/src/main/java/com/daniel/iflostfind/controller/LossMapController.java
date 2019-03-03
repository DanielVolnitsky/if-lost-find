package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.HiddenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LossMapController {

    final String LOSS_MAP_PAGE_PATH = "/loss/map";
    final String LOSS_MAP_PAGE = "lossMap";

    private final HiddenInfoService hiddenInfoService;

    @Autowired
    public LossMapController(HiddenInfoService hiddenInfoService) {
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(LOSS_MAP_PAGE_PATH)
    public String toLossMapPage(Model model) {

        String key = hiddenInfoService.getMapKey();
        model.addAttribute("google_map_key", key);

        return LOSS_MAP_PAGE;
    }
}
