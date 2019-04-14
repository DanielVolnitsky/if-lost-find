package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.domain.FindingGroup;
import com.daniel.iflostfind.service.LossGroupService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.FindingDto;
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
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "group", defaultValue = "All") String type) {

        if (page < 1) {
            return new ModelAndView("redirect:/findings?page=" + 1 + "&findingGroupName=" + type);
        }

        ModelAndView mav = new ModelAndView("findings");

        FindingGroup g = lossGroupService.getLossGroup(type).orElse(FindingGroup.ALL);

        PageableDto<List<FindingDto>> dto;
        if(g.equals(FindingGroup.ALL)){
            dto = lossService.getPaged(page, limit);
        } else {
            dto = lossService.getFilteredByGroup(page, limit, g);
        }

        PaginationInfo pagInfo = dto.getPaginationInfo();
        if (pagInfo.isOutOfBounds()) {
            return new ModelAndView("redirect:/findings?page=" + pagInfo.getTotalPages());
        }

        mav.addObject("findings", dto.getElement());
        mav.addObject("pagination", pagInfo);
        mav.addObject("filterGroup", type);

        Set<String> lg = lossGroupService.getLossGroupNames();
        mav.addObject("findingGroups", lg);

        return mav;
    }
}
