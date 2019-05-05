package com.daniel.iflostfind.controller;

import com.daniel.iflostfind.configuration.security.PersonDetails;
import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.FindingGroup;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.service.GoogleMapKeyService;
import com.daniel.iflostfind.service.LossGroupService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.dto.FindingDto;
import com.daniel.iflostfind.service.dto.FindingWithReporterDto;
import com.daniel.iflostfind.service.dto.PageableDto;
import com.daniel.iflostfind.service.dto.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class FindingController {

    private final LossService lossService;
    private final LossGroupService lossGroupService;
    private final GoogleMapKeyService googleMapsService;


    @Value("${pagination.limit.default}")
    private int limit;

    @Autowired
    public FindingController(LossService lossService, LossGroupService lossGroupService, GoogleMapKeyService googleMapsService) {
        this.lossService = lossService;
        this.lossGroupService = lossGroupService;
        this.googleMapsService = googleMapsService;
    }

    @GetMapping("/findings/nearby")
    public ModelAndView showFindingsNearby(
            @RequestParam(name = "radius") int radius,
            @RequestParam(name = "user-lat") double userLat,
            @RequestParam(name = "user-lng") double userLng) {

        Coordinate pivot = new Coordinate(userLat, userLng);
        List<FindingDto> nearest = lossService.getNearestFindings(pivot, radius, 99);

        ModelAndView mav = new ModelAndView("findings");
        mav.addObject("findings", nearest);
        mav.addObject("filterGroup", "All");
        mav.addObject("findingGroups", lossGroupService.getLossGroupNames());
        mav.addObject("google_map_key", googleMapsService.getMapKey());

        return mav;
    }

    //TODO filter or AOP
    @GetMapping("/findings")
    public ModelAndView showFindings(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "group", defaultValue = "All") String type) {

        if (page < 1) {
            return new ModelAndView("redirect:/findings?page=" + 1 + "&group=" + type);
        }

        ModelAndView mav = new ModelAndView("findings");

        FindingGroup g = lossGroupService.getLossGroup(type).orElse(FindingGroup.ALL);

        PageableDto<List<FindingDto>> dto = lossService.getFilteredByGroup(page, limit, g);

        PaginationInfo pagInfo = dto.getPaginationInfo();
        if (pagInfo.isOutOfBounds()) {
            return new ModelAndView("redirect:/findings?page=" + pagInfo.getTotalPages());
        }

        mav.addObject("findings", dto.getElement());
        mav.addObject("pagination", pagInfo);
        mav.addObject("filterGroup", type);

        Set<String> lg = lossGroupService.getLossGroupNames();
        mav.addObject("findingGroups", lg);

        mav.addObject("google_map_key", googleMapsService.getMapKey());

        return mav;
    }

    @GetMapping("/findings/report")
    public String toLossReportPage(Model m) {

        m.addAttribute("google_map_key", googleMapsService.getMapKey());
        m.addAttribute("finding", new FindingDto());
        m.addAttribute("findingGroups", FindingGroup.values());

        return "finding_report";
    }

    @PostMapping("/findings/report")
    public String reportFind(
            @ModelAttribute("finding") @Valid FindingDto dto,
            @AuthenticationPrincipal PersonDetails p) {

        User reporter = p.getUser();
        lossService.add(dto, reporter);
        return "redirect:/index";
    }

    @GetMapping("/findings/{id}")
    public ModelAndView getFindingDetails(@PathVariable("id") long findId) {

        ModelAndView mv = new ModelAndView("finding_details");

        Optional<FindingWithReporterDto> find = lossService.getAlongWithReporter(findId);
        find.ifPresent(f -> {
            mv.addObject("finding", f);
            mv.addObject("google_map_key", googleMapsService.getMapKey());
        });

        return mv;
    }
}
