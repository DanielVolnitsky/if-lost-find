package com.daniel.iflostfind.rest.controller;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.LossDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LossRestController {

    private final LossService lossService;

    @Autowired
    public LossRestController(LossService lossService) {
        this.lossService = lossService;
    }

    @GetMapping("/api/losses")
    public List<LossDto> getLossesInRadius(
            @RequestParam("pivotLat") double lat,
            @RequestParam("pivotLng") double lng,
            @RequestParam("radius") double r) {

        Coordinate pivotLocation = new Coordinate(lat, lng);
        return lossService.getAllWithinRadiusOfCoordinate(pivotLocation, r);
    }

    @GetMapping("/api/losses/nearest")
    public List<LossDto> getTopNearestLosses(
            @RequestParam("pivotLat") double lat,
            @RequestParam("pivotLng") double lng,
            @RequestParam("limit") int limit) {

        Coordinate pivotLocation = new Coordinate(lat, lng);
        return lossService.getTopNearestLosses(pivotLocation, limit);
    }
}
