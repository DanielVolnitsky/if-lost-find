package com.daniel.iflostfind.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscoveryPlace {

    private String placeId;

    private Coordinate coordinate;

    public String getPlaceId() {
        return placeId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
