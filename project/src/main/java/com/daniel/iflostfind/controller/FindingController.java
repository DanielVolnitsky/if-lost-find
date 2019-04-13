package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.service.LossGroupService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.LossDto;
import com.daniel.iflostfind.service.dto.PageableDto;
import com.daniel.iflostfind.service.dto.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class FindingController {

    private final LossService lossService;
    private final LossGroupService lossGroupService;

    @Value("${pagination.limit.default}")
    private int limit;

    @Autowired
    public FindingController(LossService lossService, LossGroupService lossGroupService) {
        this.lossService = lossService;
        this.lossGroupService = lossGroupService;
    }

    //TODO filter or AOP
    @GetMapping("/findings")
    public ModelAndView showFindings(
            @RequestParam(name = "page", defaultValue = "1") int page) {

        if (page < 1) {
            return new ModelAndView("redirect:/findings?page=" + 1);
        }

        ModelAndView mav = new ModelAndView("findings");

        PageableDto<List<LossDto>> dto = lossService.getPaged(page, limit);

        PaginationInfo pagInfo = dto.getPaginationInfo();
        if (pagInfo.isOutOfBounds()) {
            return new ModelAndView("redirect:/findings?page=" + pagInfo.getTotalPages());
        }

        mav.addObject("findings", dto.getElement());
        mav.addObject("pagination", pagInfo);

        Set<String> lg = lossGroupService.getLossGroupNames();
        mav.addObject("lossGroups", lg);

        return mav;
    }
}
