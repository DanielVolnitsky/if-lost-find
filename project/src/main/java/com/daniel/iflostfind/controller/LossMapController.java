package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.controller.converter.impl.LossConverter;
import com.daniel.iflostfind.controller.dto.LossDto;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.service.HiddenInfoService;
import com.daniel.iflostfind.service.LossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class LossMapController {

    final String LOSSES_MAP_PAGE_PATH = "/losses";
    final String LOSSES_MAP_PAGE = "lossMap";

    private final HiddenInfoService hiddenInfoService;
    private final LossService lossService;
    private final LossConverter lossConverter;

    @Autowired
    public LossMapController(HiddenInfoService hiddenInfoService, LossService lossService, LossConverter lossConverter) {
        this.hiddenInfoService = hiddenInfoService;
        this.lossService = lossService;
        this.lossConverter = lossConverter;
    }

    @GetMapping(path = LOSSES_MAP_PAGE_PATH)
    public String toMapPage(Model model) {

        String key = hiddenInfoService.getMapKey();
        model.addAttribute("google_map_key", key);

        List<Loss> losses = lossService.getAll();
        Collection<LossDto> lossDtos = lossConverter.convertEntitiesToDtos(losses);
        model.addAttribute("losses", lossDtos);

        return LOSSES_MAP_PAGE;
    }
}
