package com.daniel.iflostfind.service.dto;

import lombok.Getter;

@Getter
public class PaginationInfo {
    private final int currentPage;
    private final int totalPages;
    private final boolean first;
    private final boolean last;
    private boolean outOfBounds;

    public PaginationInfo(int currentPage, int totalPages, boolean first, boolean last) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.first = first;
        this.last = last;
        this.outOfBounds = totalPages < currentPage && totalPages != 0;
    }
}
