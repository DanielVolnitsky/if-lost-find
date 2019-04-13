package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.domain.LossGroup;
import com.daniel.iflostfind.service.GoogleMapService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.LossDto;
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

    @GetMapping("/loss/report")
    public String toLossReportPage(Model m) {

        m.addAttribute("google_map_key", googleMapService.getMapKey());
        m.addAttribute("loss", new LossDto());
        m.addAttribute("lossTypes", LossGroup.values());

        return "loss_report";
    }

    @PostMapping("/loss/report")
    public String createLoss(@ModelAttribute("loss") @Valid LossDto lossDto) {

        lossService.add(lossDto);
        return "redirect:/loss/report";
    }
}
