package com.daniel.iflostfind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    final String ACCOUNT_PATH = "/account";
    final String ACCOUNT_PAGE = "account";

    @GetMapping(path = ACCOUNT_PATH)
    public String toAccountPage() { return ACCOUNT_PAGE; }

}
