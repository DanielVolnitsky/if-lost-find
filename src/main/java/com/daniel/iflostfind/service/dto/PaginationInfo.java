package com.daniel.iflostfind.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class PaginationInfo {
    private final int currentPage;
    private final int totalPages;
    private final boolean first;
    private final boolean last;

    @Setter
    private boolean outOfBounds;
}
