package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.LossGroupService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.LossDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class FindingListController {

    private final LossService lossService;
    private final LossGroupService lossGroupService;

    @Autowired
    public FindingListController(LossService lossService, LossGroupService lossGroupService) {
        this.lossService = lossService;
        this.lossGroupService = lossGroupService;
    }

    @GetMapping("/findings")
    public ModelAndView toPage() {

        ModelAndView mav = new ModelAndView("found_list");

        List<LossDto> f = lossService.getAll();
        mav.addObject("findings", f);

        Set<String> lg = lossGroupService.getLossGroupNames();
        mav.addObject("lossGroups", lg);

        return mav;
    }
}
