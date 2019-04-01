package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import com.daniel.iflostfind.service.GoogleMapApiService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.converter.impl.LossConverter;
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

    private final LossConverter lossConverter;
    private final LossService lossService;
    private final GoogleMapApiService googleMapApiService;

    @Autowired
    public LossController(LossConverter lossConverter, LossService lossService, GoogleMapApiService googleMapApiService) {
        this.lossConverter = lossConverter;
        this.lossService = lossService;
        this.googleMapApiService = googleMapApiService;
    }

    @GetMapping("/loss/report")
    public String toLossReportPage(Model m) {

        m.addAttribute("google_map_key", googleMapApiService.getMapKey());
        m.addAttribute("loss", new LossDto());
        m.addAttribute("lossTypes", LossType.values());

        return "loss_report";
    }

    @PostMapping("/loss/report")
    public String createLoss(@ModelAttribute("loss") @Valid LossDto lossDto) {

        lossService.add(lossDto);
        return "redirect:/loss/report";
    }
}
