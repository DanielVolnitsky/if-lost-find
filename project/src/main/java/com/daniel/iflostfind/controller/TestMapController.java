package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.HiddenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestMapController {

    final String TEST_MAP_PAGE_PATH = "/test";
    final String TEST_MAP_PAGE = "test_map";

    private final HiddenInfoService hiddenInfoService;

    @Autowired
    public TestMapController(HiddenInfoService hiddenInfoService) {
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(path = TEST_MAP_PAGE_PATH)
    public String toMapPage(Model model) {

        String key = hiddenInfoService.getMapKey();
        model.addAttribute("google_map_key", key);

        return TEST_MAP_PAGE;
    }
}
