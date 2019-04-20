package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.FindingGroup;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.service.dto.FindingDto;
import com.daniel.iflostfind.service.dto.FindingWithReporterDto;
import com.daniel.iflostfind.service.dto.PageableDto;
import com.daniel.iflostfind.service.util.PageableService;

import java.util.List;
import java.util.Optional;

public interface LossService extends PageableService<Integer, Integer> {
    void add(FindingDto loss, User user);

    List<FindingDto> getAll();

    List<FindingDto> getAllWithinRadiusOfCoordinate(Coordinate coordinate, double radiusKm);

    List<FindingDto> getTopNearestLosses(Coordinate pivot, int limit);

    PageableDto<List<FindingDto>> getFilteredByGroup(Integer page, Integer limit, FindingGroup group);

    Optional<FindingDto> getById(long lossId);

    Optional<FindingWithReporterDto> getAlongWithReporter(long lossId);
}
