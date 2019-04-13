package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.LossGroup;
import com.daniel.iflostfind.service.dto.LossDto;
import com.daniel.iflostfind.service.dto.PageableDto;
import com.daniel.iflostfind.service.util.PageableService;

import java.util.List;
import java.util.Optional;

public interface LossService extends PageableService<Integer, Integer> {
    void add(LossDto loss);

    List<LossDto> getAll();

    List<LossDto> getAllWithinRadiusOfCoordinate(Coordinate coordinate, double radiusKm);

    List<LossDto> getTopNearestLosses(Coordinate pivot, int limit);

    PageableDto<List<LossDto>> getFilteredByGroup(Integer page, Integer limit, LossGroup group);

    Optional<LossDto> getById(long lossId);
}
