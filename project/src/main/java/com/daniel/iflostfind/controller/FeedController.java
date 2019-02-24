package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.MissingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {

    final String FEED_PATH = "/feed";
    final String FEED_PAGE = "feed";

    private final MissingItemService missingItemService;

    @Autowired
    public FeedController(MissingItemService missingItemService) {
        this.missingItemService = missingItemService;
    }

    @GetMapping(path = FEED_PATH)
    public String toFeedPage(Model model) {
        model.addAttribute("items", missingItemService.getAll());
        return FEED_PAGE;
    }
}
