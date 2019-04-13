package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.service.dto.LossDto;
import com.daniel.iflostfind.service.util.PageableService;

import java.util.List;
import java.util.Optional;

public interface LossService extends PageableService<Integer, Integer> {
    void add(LossDto loss);

    List<LossDto> getAll();

    List<LossDto> getAllWithinRadiusOfCoordinate(Coordinate coordinate, double radiusKm);

    List<LossDto> getTopNearestLosses(Coordinate pivot, int limit);

    Optional<LossDto> getById(long lossId);
}
