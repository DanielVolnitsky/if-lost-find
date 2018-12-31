package com.daniel.iflostfind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {

    final String FEED_PATH = "/feed";
    final String FEED_PAGE = "feed";

    @GetMapping(path = FEED_PATH)
    public String toFeedPage() { return FEED_PAGE;}

}
