package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.DiscoveryPlace;
import com.daniel.iflostfind.domain.Finding;
import com.daniel.iflostfind.domain.FindingGroup;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.service.CoordinateService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.converter.impl.FindingConverter;
import com.daniel.iflostfind.service.dto.FindingDto;
import com.daniel.iflostfind.service.dto.PageableDto;
import com.daniel.iflostfind.service.dto.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class LossServiceImpl implements LossService {

    private final CoordinateService coordinateService;
    private final LossRepository lossRepository;
    private final FindingConverter converter;

    @Autowired
    public LossServiceImpl(CoordinateService coordinateService,
                           LossRepository lossRepository,
                           FindingConverter converter) {
        this.coordinateService = coordinateService;
        this.lossRepository = lossRepository;
        this.converter = converter;
    }

    @Override
    public void add(FindingDto dto) {
        Finding finding = converter.convertDtoToEntity(dto);
        lossRepository.save(finding);
    }

    @Override
    public List<FindingDto> getAll() {
        List<Finding> findings = (List<Finding>) lossRepository.findAll();
        return converter.convertEntitiesToDtos(findings);
    }

    //TODO optimize
    @Override
    public List<FindingDto> getAllWithinRadiusOfCoordinate(Coordinate pivot, double radius) {
        List<Finding> all = (List<Finding>) lossRepository.findAll();
        List<Finding> inRadius = all.stream()
                .filter(l -> isLossWithinRadius(pivot, l, radius))
                .collect(toList());

        return converter.convertEntitiesToDtos(inRadius);
    }

    //TODO optimize
    @Override
    public List<FindingDto> getTopNearestLosses(Coordinate pivot, int limit) {
        List<Finding> all = (List<Finding>) lossRepository.findAll();
        List<Finding> nearest = all.stream()
                .sorted(Comparator.comparingDouble(l -> {
                    DiscoveryPlace dp = l.getDiscoveryPlace();
                    return coordinateService.getDistanceBetweenCoordinates(pivot, dp.getCoordinate());
                }))
                .limit(limit)
                .collect(toList());

        return converter.convertEntitiesToDtos(nearest);
    }

    @Override
    public Optional<FindingDto> getById(long lossId) {
        Optional<Finding> loss = lossRepository.findById(lossId);
        return loss.map(converter::convertEntityToDto);
    }

    @Override
    public PageableDto<List<FindingDto>> getPaged(Integer pageNumber, Integer limit) {
        Pageable pageable = PageRequest.of(pageNumber - 1, limit);

        Page<Finding> page = lossRepository.findAll(pageable);

        PaginationInfo pi = PaginationInfo.builder()
                .currentPage(pageNumber)
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast()).build();

        boolean outOfBounds = page.getTotalPages() < pageNumber & page.getTotalPages() != 0;
        pi.setOutOfBounds(outOfBounds);

        List<Finding> findings = page.stream().collect(toList());
        List<FindingDto> findingDtos = converter.convertEntitiesToDtos(findings);

        return new PageableDto<>(pi, findingDtos);
    }

    @Override
    public PageableDto<List<FindingDto>> getFilteredByGroup(Integer pageNumber, Integer limit, FindingGroup group) {
        Pageable pageable = PageRequest.of(pageNumber - 1, limit);

        Page<Finding> page = lossRepository.findAllByFindingGroup(group, pageable);

        PaginationInfo pi = PaginationInfo.builder()
                .currentPage(pageNumber)
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast()).build();

        boolean outOfBounds = page.getTotalPages() < pageNumber & page.getTotalPages() != 0;
        pi.setOutOfBounds(outOfBounds);

        List<Finding> findings = page.stream().collect(toList());
        List<FindingDto> findingDtos = converter.convertEntitiesToDtos(findings);

        return new PageableDto<>(pi, findingDtos);
    }

    private boolean isLossWithinRadius(Coordinate pivot, Finding finding, double radius) {
        DiscoveryPlace dp = finding.getDiscoveryPlace();
        return coordinateService.getDistanceBetweenCoordinates(pivot, dp.getCoordinate()) <= radius;
    }
}
