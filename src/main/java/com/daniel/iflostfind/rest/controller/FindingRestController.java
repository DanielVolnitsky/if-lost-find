package com.daniel.iflostfind.rest.controller;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.FindingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindingRestController {

    private final LossService lossService;

    @Autowired
    public FindingRestController(LossService lossService) {
        this.lossService = lossService;
    }

    @GetMapping("/api/findings")
    public List<FindingDto> getFindingsInRadius(
            @RequestParam("pivotLat") double lat,
            @RequestParam("pivotLng") double lng,
            @RequestParam("radius") double r) {

        Coordinate pivotLocation = new Coordinate(lat, lng);
        return lossService.getAllWithinRadiusOfCoordinate(pivotLocation, r);
    }
}
