package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.service.LossService;
import com.daniel.iflostfind.service.converter.impl.LossConverter;
import com.daniel.iflostfind.service.dto.LossDto;
import com.daniel.iflostfind.service.util.CoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LossServiceImpl implements LossService {

    private final CoordinateService coordinateService;
    private final LossRepository lossRepository;
    private final LossConverter converter;

    @Autowired
    public LossServiceImpl(CoordinateService coordinateService, LossRepository lossRepository, LossConverter converter) {
        this.coordinateService = coordinateService;
        this.lossRepository = lossRepository;
        this.converter = converter;
    }

    @Override
    public void add(Loss loss) {
        lossRepository.save(loss);
    }

    @Override
    public List<Loss> getAll() {
        return (List<Loss>) lossRepository.findAll();
    }

    //TODO optimize
    @Override
    public List<LossDto> getAllWithinRadiusOfCoordinate(Coordinate pivot, double radius) {
        List<Loss> all = getAll();
        List<Loss> inRadius = all.stream()
                .filter(l -> isLossWithinRadius(pivot, l, radius))
                .collect(toList());

        return converter.convertEntitiesToDtos(inRadius);
    }

    //TODO optimize
    @Override
    public List<LossDto> getTopNearestLosses(Coordinate pivot, int limit) {
        List<Loss> all = getAll();
        List<Loss> nearest = all.stream()
                .sorted(Comparator.comparingDouble(l -> coordinateService.getDistanceBetweenCoordinates(pivot, l.getCoordinate())))
                .limit(limit)
                .collect(toList());

        return converter.convertEntitiesToDtos(nearest);
    }

    private boolean isLossWithinRadius(Coordinate pivot, Loss loss, double radius) {
        return coordinateService.getDistanceBetweenCoordinates(pivot, loss.getCoordinate()) <= radius;
    }
}
