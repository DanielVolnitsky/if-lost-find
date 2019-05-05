package com.daniel.iflostfind.service.converter.impl;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.DiscoveryPlace;
import com.daniel.iflostfind.domain.Finding;
import com.daniel.iflostfind.domain.FindingGroup;
import com.daniel.iflostfind.service.converter.DtoToEntityConverter;
import com.daniel.iflostfind.service.converter.EntityToDtoCollectionConverter;
import com.daniel.iflostfind.service.converter.EntityToDtoConverter;
import com.daniel.iflostfind.service.dto.FindingDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FindingConverter implements
        EntityToDtoConverter<Finding, FindingDto>,
        DtoToEntityConverter<Finding, FindingDto>,
        EntityToDtoCollectionConverter<Finding, FindingDto> {

    @Override
    public Finding convertDtoToEntity(FindingDto dto) {

        FindingGroup fg = FindingGroup.valueOf(dto.getFindingGroupName());

        double lat = dto.getLatitude();
        double lng = dto.getLongitude();

        String placeId = dto.getDiscoveryPlaceId();
        Coordinate coordinate = new Coordinate(lat, lng);
        DiscoveryPlace dp = new DiscoveryPlace(placeId, coordinate);

        return Finding.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .findingGroup(fg)
                .dateFound(dto.getDateFound())
                .discoveryPlace(dp)
                .build();
    }

    @Override
    public FindingDto convertEntityToDto(Finding finding) {
        FindingDto dto = new FindingDto();

        dto.setId(finding.getId());
        dto.setName(finding.getName());
        dto.setDescription(finding.getDescription());

        String lossType = finding.getFindingGroup().toString();
        dto.setFindingGroupName(lossType);

        dto.setDateFound(finding.getDateFound());

        DiscoveryPlace dp = finding.getDiscoveryPlace();

        String placeId = dp.getPlaceId();
        dto.setDiscoveryPlaceId(placeId);

        Coordinate co = dp.getCoordinate();
        dto.setLatitude(co.getLatitude());
        dto.setLongitude(co.getLongitude());

        dto.setDaysOld(finding.getDaysOld());

        return dto;
    }

    @Override
    public List<FindingDto> convertEntitiesToDtos(Collection<Finding> e) {
        return e.stream().map(this::convertEntityToDto).collect(toList());
    }
}
