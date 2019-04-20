package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.GoogleMapService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.FindingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FindingDetailsController {

    private final LossService lossService;
    private final GoogleMapService googleMapService;

    @Autowired
    public FindingDetailsController(LossService lossService, GoogleMapService googleMapService) {
        this.lossService = lossService;
        this.googleMapService = googleMapService;
    }

    @GetMapping("/findings/{id}")
    public ModelAndView toLossDetailsPage(@PathVariable("id") long lossId) {

        ModelAndView mv = new ModelAndView("finding_details");

        Optional<FindingDto> loss = lossService.getById(lossId);
        loss.ifPresent(l -> {
            mv.addObject("finding", l);
            mv.addObject("google_map_key", googleMapService.getMapKey());
        });

        return mv;
    }
}
