package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.converter.impl.LossConverter;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import com.daniel.iflostfind.dto.LossDto;
import com.daniel.iflostfind.service.HiddenInfoService;
import com.daniel.iflostfind.service.LossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LossController {

    final static String LOSS_PAGE_PATH = "/map";

    private final LossConverter lossConverter;
    private final LossService lossService;
    private final HiddenInfoService hiddenInfoService;

    @Autowired
    public LossController(LossConverter lossConverter, LossService lossService, HiddenInfoService hiddenInfoService) {
        this.lossConverter = lossConverter;
        this.lossService = lossService;
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(path = LOSS_PAGE_PATH)
    public String toLossPage(Model m) {

        m.addAttribute("google_map_key", hiddenInfoService.getMapKey());
        m.addAttribute("loss", new LossDto());
        m.addAttribute("lossTypes", LossType.values());

        return "map";
    }

    @PostMapping(path = LOSS_PAGE_PATH)
    public String createLoss(@ModelAttribute("loss") @Valid LossDto lossDto) {

        Loss loss = lossConverter.reverseConvert(lossDto);
        lossService.add(loss);

        return "redirect:" + LOSS_PAGE_PATH;
    }
}
