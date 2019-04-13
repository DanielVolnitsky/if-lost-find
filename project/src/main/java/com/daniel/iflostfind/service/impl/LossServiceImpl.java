package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.service.CoordinateService;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.converter.impl.LossConverter;
import com.daniel.iflostfind.service.dto.LossDto;
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
    private final LossConverter converter;

    @Autowired
    public LossServiceImpl(CoordinateService coordinateService,
                           LossRepository lossRepository,
                           LossConverter converter) {
        this.coordinateService = coordinateService;
        this.lossRepository = lossRepository;
        this.converter = converter;
    }

    @Override
    public void add(LossDto dto) {
        Loss loss = converter.convertDtoToEntity(dto);
        lossRepository.save(loss);
    }

    @Override
    public List<LossDto> getAll() {
        List<Loss> losses = (List<Loss>) lossRepository.findAll();
        return converter.convertEntitiesToDtos(losses);
    }

    //TODO optimize
    @Override
    public List<LossDto> getAllWithinRadiusOfCoordinate(Coordinate pivot, double radius) {
        List<Loss> all = (List<Loss>) lossRepository.findAll();
        List<Loss> inRadius = all.stream()
                .filter(l -> isLossWithinRadius(pivot, l, radius))
                .collect(toList());

        return converter.convertEntitiesToDtos(inRadius);
    }

    //TODO optimize
    @Override
    public List<LossDto> getTopNearestLosses(Coordinate pivot, int limit) {
        List<Loss> all = (List<Loss>) lossRepository.findAll();
        List<Loss> nearest = all.stream()
                .sorted(Comparator.comparingDouble(l -> coordinateService.getDistanceBetweenCoordinates(pivot, l.getCoordinate())))
                .limit(limit)
                .collect(toList());

        return converter.convertEntitiesToDtos(nearest);
    }

    @Override
    public Optional<LossDto> getById(long lossId) {
        Optional<Loss> loss = lossRepository.findById(lossId);
        return loss.map(converter::convertEntityToDto);
    }

    @Override
    public PageableDto<List<LossDto>> getPaged(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Loss> lossPage = lossRepository.findAll(pageable);

        PaginationInfo pi = PaginationInfo.builder()
                .currentPage(page)
                .totalPages(lossPage.getTotalPages())
                .first(lossPage.isFirst())
                .last(lossPage.isLast()).build();

        pi.setOutOfBounds(lossPage.getTotalPages() < page);

        List<Loss> losses = lossPage.stream().collect(toList());
        List<LossDto> lossDtos = converter.convertEntitiesToDtos(losses);

        return new PageableDto<>(pi, lossDtos);

    }

    private boolean isLossWithinRadius(Coordinate pivot, Loss loss, double radius) {
        return coordinateService.getDistanceBetweenCoordinates(pivot, loss.getCoordinate()) <= radius;
    }
}
