package com.daniel.iflostfind.controller.converter.impl;

import com.daniel.iflostfind.controller.converter.DtoToEntityConverter;
import com.daniel.iflostfind.controller.converter.EntityToDtoCollectionConverter;
import com.daniel.iflostfind.controller.converter.EntityToDtoConverter;
import com.daniel.iflostfind.controller.dto.LossDto;
import com.daniel.iflostfind.domain.Location;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Component
public class LossConverter implements
        EntityToDtoConverter<Loss, LossDto>,
        DtoToEntityConverter<Loss, LossDto>,
        EntityToDtoCollectionConverter<Loss, LossDto> {

    @Override
    public Loss convertDtoToEntity(LossDto dto) {

        LossType lossType = LossType.valueOf(dto.getType());

        Location location = Location.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        return Loss.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .type(lossType)
                .lossDate(dto.getLossDate())
                .location(location)
                .build();
    }

    @Override
    public LossDto convertEntityToDto(Loss loss) {
        LossDto dto = new LossDto();

        dto.setId(loss.getId());
        dto.setName(dto.getName());
        dto.setDescription(loss.getDescription());

        String lossType = loss.getType().toString();
        dto.setType(lossType);

        Location lossLoc = loss.getLocation();
        dto.setLatitude(lossLoc.getLatitude());
        dto.setLongitude(lossLoc.getLongitude());

        return dto;
    }

    @Override
    public Collection<LossDto> convertEntitiesToDtos(Collection<Loss> e) {
        return e.stream().map(this::convertEntityToDto).collect(toList());
    }
}
