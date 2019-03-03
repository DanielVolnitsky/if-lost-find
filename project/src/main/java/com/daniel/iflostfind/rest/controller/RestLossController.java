package com.daniel.iflostfind.rest.controller;

import com.daniel.iflostfind.controller.converter.impl.LossConverter;
import com.daniel.iflostfind.controller.dto.LossDto;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.service.LossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class RestLossController {

    private final LossService lossService;
    private final LossConverter lossConverter;

    @Autowired
    public RestLossController(LossService lossService, LossConverter lossConverter) {
        this.lossService = lossService;
        this.lossConverter = lossConverter;
    }

    @GetMapping("/api/losses")
    public List<LossDto> getAllLosses() {

        List<Loss> losses = lossService.getAll();
        Collection<LossDto> lossDtos = lossConverter.convertEntitiesToDtos(losses);

        return (List<LossDto>) lossDtos;
    }
}
