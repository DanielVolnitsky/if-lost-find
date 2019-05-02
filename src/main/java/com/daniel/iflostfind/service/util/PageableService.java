package com.daniel.iflostfind.service.util;

import com.daniel.iflostfind.service.dto.PageableDto;

import java.util.List;

public interface PageableService<T, P extends Number, L extends Number> {
    PageableDto<List<T>> getPaged(P page, L limit);
}
