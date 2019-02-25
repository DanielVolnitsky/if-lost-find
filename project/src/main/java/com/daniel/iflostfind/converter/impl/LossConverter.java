package com.daniel.iflostfind.converter.impl;

import com.daniel.iflostfind.converter.ReversibleDtoConverter;
import com.daniel.iflostfind.domain.Location;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.domain.LossType;
import com.daniel.iflostfind.dto.LossDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LossConverter implements ReversibleDtoConverter<Loss, LossDto> {


    @Override
    public Loss reverseConvert(LossDto dto) {
        Loss loss = new Loss();
        loss.setId(dto.getId());
        loss.setName(dto.getName());
        loss.setDescription(dto.getDescription());

        String dtoType = dto.getType();
        LossType lossType = LossType.valueOf(dtoType);
        loss.setType(lossType);

        String dtoLossDate = dto.getLossDate();
        LocalDate lossDate = LocalDate.parse(dtoLossDate);
        loss.setLossDate(lossDate);

        String dtoLatitude = dto.getLatitude();
        String dtoLongitude = dto.getLongitude();
        double latitude = Double.parseDouble(dtoLatitude);
        double longitude = Double.parseDouble(dtoLongitude);
        Location location = Location.builder().latitude(latitude).longitude(longitude).build();
        loss.setLocation(location);

        //TODO
        loss.setFinder(null);

        return loss;
    }

    @Override
    public LossDto convert(Loss loss) {
        throw new UnsupportedOperationException();
    }
}
