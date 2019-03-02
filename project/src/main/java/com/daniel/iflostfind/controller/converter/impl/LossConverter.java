package com.daniel.iflostfind.controller.converter.impl;

import com.daniel.iflostfind.controller.converter.ReversibleDtoConverter;
import com.daniel.iflostfind.controller.dto.LossDto;
import com.daniel.iflostfind.domain.Location;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LossConverter implements ReversibleDtoConverter<Loss, LossDto> {


    @Override
    public Loss reverseConvert(LossDto dto) {

        String dtoType = dto.getType();
        LossType lossType = LossType.valueOf(dtoType);

        String dtoLossDate = dto.getLossDate();
        LocalDate lossDate = LocalDate.parse(dtoLossDate);

        String dtoLatitude = dto.getLatitude();
        String dtoLongitude = dto.getLongitude();
        double latitude = Double.parseDouble(dtoLatitude);
        double longitude = Double.parseDouble(dtoLongitude);
        Location location = Location.builder().latitude(latitude).longitude(longitude).build();

        return Loss.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .type(lossType)
                .lossDate(lossDate)
                .location(location)
                .build();
    }

    @Override
    public LossDto convert(Loss loss) {
        throw new UnsupportedOperationException();
    }
}
