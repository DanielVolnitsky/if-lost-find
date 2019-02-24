package com.daniel.iflostfind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MissingItemController {

    final String LOSS_PAGE_PATH = "/loss";
    final String LOSS_PAGE = "loss";

    @PostMapping(path = LOSS_PAGE_PATH)
    public void m(@ModelAttribute("loss") @Valid LossDto lossDto, BindingResult br) {

    }
}
