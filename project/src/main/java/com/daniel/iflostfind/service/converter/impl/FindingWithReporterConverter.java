package com.daniel.iflostfind.service.converter.impl;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.DiscoveryPlace;
import com.daniel.iflostfind.domain.Finding;
import com.daniel.iflostfind.domain.User;
import com.daniel.iflostfind.service.converter.EntityToDtoConverter;
import com.daniel.iflostfind.service.dto.FindingWithReporterDto;
import com.daniel.iflostfind.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindingWithReporterConverter implements EntityToDtoConverter<Finding, FindingWithReporterDto> {

    private final UserConverter userConverter;

    @Autowired
    public FindingWithReporterConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }


    @Override
    public FindingWithReporterDto convertEntityToDto(Finding finding) {
        FindingWithReporterDto dto = new FindingWithReporterDto();

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

        User reporter = finding.getReporter();
        UserDto reporterDto = userConverter.convertEntityToDto(reporter);
        dto.setReporter(reporterDto);

        return dto;
    }
}
