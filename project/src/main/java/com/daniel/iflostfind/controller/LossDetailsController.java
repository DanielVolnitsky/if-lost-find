package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.GoogleMapApiService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.LossDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LossDetailsController {

    private final LossService lossService;
    private final GoogleMapApiService googleMapApiService;

    @Autowired
    public LossDetailsController(LossService lossService, GoogleMapApiService googleMapApiService) {
        this.lossService = lossService;
        this.googleMapApiService = googleMapApiService;
    }

    @GetMapping("/loss/{id}")
    public ModelAndView toLossDetailsPage(@PathVariable("id") long lossId) {

        ModelAndView mv = new ModelAndView("loss_details");

        Optional<LossDto> loss = lossService.getById(lossId);
        loss.ifPresent(l -> {
            mv.addObject("loss", l);
            mv.addObject("google_map_key", googleMapApiService.getMapKey());
        });

        return mv;
    }
}
