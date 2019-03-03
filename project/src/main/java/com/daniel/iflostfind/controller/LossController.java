package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.controller.converter.impl.LossConverter;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import com.daniel.iflostfind.controller.dto.LossDto;
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

    final static String LOSS_REPORT_PAGE_PATH = "/loss/report";

    private final LossConverter lossConverter;
    private final LossService lossService;
    private final HiddenInfoService hiddenInfoService;

    @Autowired
    public LossController(LossConverter lossConverter, LossService lossService, HiddenInfoService hiddenInfoService) {
        this.lossConverter = lossConverter;
        this.lossService = lossService;
        this.hiddenInfoService = hiddenInfoService;
    }

    @GetMapping(LOSS_REPORT_PAGE_PATH)
    public String toLossReportPage(Model m) {

        m.addAttribute("google_map_key", hiddenInfoService.getMapKey());
        m.addAttribute("loss", new LossDto());
        m.addAttribute("lossTypes", LossType.values());

        return "lossReport";
    }

    @PostMapping(LOSS_REPORT_PAGE_PATH)
    public String createLoss(@ModelAttribute("loss") @Valid LossDto lossDto) {

        Loss loss = lossConverter.convertDtoToEntity(lossDto);
        lossService.add(loss);

        return "redirect:" + LOSS_REPORT_PAGE_PATH;
    }
}
