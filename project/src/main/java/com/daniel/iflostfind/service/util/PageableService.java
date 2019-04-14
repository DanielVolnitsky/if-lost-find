package com.daniel.iflostfind.service.util;

import com.daniel.iflostfind.service.dto.FindingDto;
import com.daniel.iflostfind.service.dto.PageableDto;

import java.util.List;

public interface PageableService<P extends Number, L extends Number> {
    PageableDto<List<FindingDto>> getPaged(P page, L limit);
}
