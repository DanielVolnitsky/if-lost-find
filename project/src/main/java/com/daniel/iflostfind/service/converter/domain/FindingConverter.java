package com.daniel.iflostfind.service.converter.domain;

import com.daniel.iflostfind.domain.Finding;
import com.daniel.iflostfind.service.converter.DtoToEntityConverter;
import com.daniel.iflostfind.service.converter.EntityToDtoCollectionConverter;
import com.daniel.iflostfind.service.converter.EntityToDtoConverter;
import com.daniel.iflostfind.service.dto.FindingDto;

public interface FindingConverter extends
        EntityToDtoConverter<Finding, FindingDto>,
        DtoToEntityConverter<Finding, FindingDto>,
        EntityToDtoCollectionConverter<Finding, FindingDto> {

}
