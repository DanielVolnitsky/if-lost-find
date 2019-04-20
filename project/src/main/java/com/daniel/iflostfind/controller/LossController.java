package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.domain.FindingGroup;
import com.daniel.iflostfind.service.GoogleMapService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.FindingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LossController {

    private final LossService lossService;
    private final GoogleMapService googleMapService;

    @Autowired
    public LossController(LossService lossService, GoogleMapService googleMapsService) {
        this.lossService = lossService;
        this.googleMapService = googleMapsService;
    }

    @GetMapping("/findings/report")
    public String toLossReportPage(Model m) {

        m.addAttribute("google_map_key", googleMapService.getMapKey());
        m.addAttribute("finding", new FindingDto());
        m.addAttribute("findingGroups", FindingGroup.values());

        return "finding_report";
    }

    @PostMapping("/findings/report")
    public String createLoss(@ModelAttribute("finding") @Valid FindingDto dto) {

        lossService.add(dto);
        return "redirect:/findings/report";
    }
}
