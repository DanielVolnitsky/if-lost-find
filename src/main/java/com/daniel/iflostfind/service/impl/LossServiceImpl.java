package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.*;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.service.CoordinateService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.converter.impl.FindingConverter;
import com.daniel.iflostfind.service.converter.impl.FindingWithReporterConverter;
import com.daniel.iflostfind.service.dto.FindingDto;
import com.daniel.iflostfind.service.dto.FindingWithReporterDto;
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
    private final FindingConverter findingConverter;
    private final FindingWithReporterConverter findingWithReporterConverter;

    @Autowired
    public LossServiceImpl(CoordinateService coordinateService,
                           LossRepository lossRepository,
                           FindingConverter findingConverter,
                           FindingWithReporterConverter findingWithReporterConverter) {

        this.coordinateService = coordinateService;
        this.lossRepository = lossRepository;
        this.findingConverter = findingConverter;
        this.findingWithReporterConverter = findingWithReporterConverter;
    }

    @Override
    public void add(FindingDto dto, User user) {
        Finding finding = findingConverter.convertDtoToEntity(dto);
        finding.setReporter(user);
        lossRepository.save(finding);
    }

    @Override
    public List<FindingDto> getAll() {
        List<Finding> findings = (List<Finding>) lossRepository.findAll();
        return findingConverter.convertEntitiesToDtos(findings);
    }

    //TODO optimize
    @Override
    public List<FindingDto> getAllWithinRadiusOfCoordinate(Coordinate pivot, double radius) {
        List<Finding> all = (List<Finding>) lossRepository.findAll();
        List<Finding> inRadius = all.stream()
                .filter(l -> isLossWithinRadius(pivot, l, radius))
                .collect(toList());

        return findingConverter.convertEntitiesToDtos(inRadius);
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

        return findingConverter.convertEntitiesToDtos(nearest);
    }

    @Override
    public Optional<FindingWithReporterDto> getAlongWithReporter(long lossId) {
        Optional<Finding> loss = lossRepository.findById(lossId);
        return loss.map(findingWithReporterConverter::convertEntityToDto);
    }

    @Override
    public PageableDto<List<FindingDto>> getFilteredByGroup(Integer pageNumber, Integer limit, FindingGroup group) {
        Pageable pageable = PageRequest.of(pageNumber - 1, limit);

        Page<Finding> result;
        if (group.equals(FindingGroup.ALL)) {
            result = lossRepository.findAll(pageable);
        } else {
            result = lossRepository.findAllByFindingGroup(group, pageable);
        }

        PaginationInfo pagingInfo = new PaginationInfo(
                pageNumber, result.getTotalPages(), result.isFirst(), result.isLast());

        List<Finding> findings = result.stream().collect(toList());
        List<FindingDto> dtos = findingConverter.convertEntitiesToDtos(findings);

        return new PageableDto<>(pagingInfo, dtos);
    }

    private boolean isLossWithinRadius(Coordinate pivot, Finding finding, double radius) {
        DiscoveryPlace dp = finding.getDiscoveryPlace();
        return coordinateService.getDistanceBetweenCoordinates(pivot, dp.getCoordinate()) <= radius;
    }
}
