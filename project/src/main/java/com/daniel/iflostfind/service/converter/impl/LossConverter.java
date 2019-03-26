package com.daniel.iflostfind.service.converter.impl;

import com.daniel.iflostfind.service.converter.DtoToEntityConverter;
import com.daniel.iflostfind.service.converter.EntityToDtoCollectionConverter;
import com.daniel.iflostfind.service.converter.EntityToDtoConverter;
import com.daniel.iflostfind.service.dto.LossDto;
import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class LossConverter implements
        EntityToDtoConverter<Loss, LossDto>,
        DtoToEntityConverter<Loss, LossDto>,
        EntityToDtoCollectionConverter<Loss, LossDto> {

    @Override
    public Loss convertDtoToEntity(LossDto dto) {

        LossType lossType = LossType.valueOf(dto.getType());

        Coordinate coordinate = Coordinate.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        return Loss.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .type(lossType)
                .lossDate(dto.getLossDate())
                .coordinate(coordinate)
                .build();
    }

    @Override
    public LossDto convertEntityToDto(Loss loss) {
        LossDto dto = new LossDto();

        dto.setId(loss.getId());
        dto.setName(loss.getName());
        dto.setDescription(loss.getDescription());

        String lossType = loss.getType().toString();
        dto.setType(lossType);

        dto.setLossDate(loss.getLossDate());

        Coordinate lossLoc = loss.getCoordinate();
        dto.setLatitude(lossLoc.getLatitude());
        dto.setLongitude(lossLoc.getLongitude());

        return dto;
    }

    @Override
    public List<LossDto> convertEntitiesToDtos(Collection<Loss> e) {
        return e.stream().map(this::convertEntityToDto).collect(toList());
    }
}
