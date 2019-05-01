package com.daniel.iflostfind.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PageableDto<T> {
    private final PaginationInfo paginationInfo;
    private final T element;
}
