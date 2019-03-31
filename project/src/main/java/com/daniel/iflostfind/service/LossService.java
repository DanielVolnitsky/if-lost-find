package com.daniel.iflostfind.service;

import com.daniel.iflostfind.service.dto.LossDto;
import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.Loss;

import java.util.List;

public interface LossService {
    void add(Loss loss);

    List<Loss> getAll();

    List<LossDto> getAllWithinRadiusOfCoordinate(Coordinate coordinate, double radiusKm);

    List<LossDto> getTopNearestLosses(Coordinate pivot, int limit);
}
