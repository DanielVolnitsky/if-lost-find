//package com.daniel.iflostfind.controller;
//
//import com.daniel.iflostfind.service.HiddenInfoService;
//import com.daniel.iflostfind.service.MissingItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class MapController {
//
//    final String MAP_PAGE_PATH = "/map";
//    final String MAP_PAGE = "map";
//
//    private final HiddenInfoService hiddenInfoService;
//    private final MissingItemService missingItemService;
//
//    @Autowired
//    public MapController(HiddenInfoService hiddenInfoService, MissingItemService missingItemService) {
//        this.hiddenInfoService = hiddenInfoService;
//        this.missingItemService = missingItemService;
//    }
//
//    @GetMapping(path = MAP_PAGE_PATH)
//    public String toMapPage(Model model) {
//
//        String key = hiddenInfoService.getMapKey();
//        model.addAttribute("google_map_key", key);
//
//        model.addAttribute("items", missingItemService.getAll());
//
//        return MAP_PAGE;
//    }
//}
