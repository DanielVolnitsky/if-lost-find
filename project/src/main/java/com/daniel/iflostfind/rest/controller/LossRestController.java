package com.daniel.iflostfind.rest.controller;

import com.daniel.iflostfind.controller.converter.impl.LossConverter;
import com.daniel.iflostfind.controller.dto.LossDto;
import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.service.LossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LossRestController {

    private final LossService lossService;
    private final LossConverter lossConverter;

    @Autowired
    public LossRestController(LossService lossService, LossConverter lossConverter) {
        this.lossService = lossService;
        this.lossConverter = lossConverter;
    }

    @GetMapping("/api/losses")
    public List<LossDto> getNearbyLosses(
            @RequestParam("pivotLat") double lat,
            @RequestParam("pivotLng") double lng,
            @RequestParam("radius") double r) {

        Coordinate pivot = new Coordinate(lat, lng);
        List<Loss> losses = lossService.getAllWithinRadiusOfCoordinate(pivot, r);

        return (List<LossDto>) lossConverter.convertEntitiesToDtos(losses);
    }
}
