package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.Coordinate;

public interface CoordinateService {

    /**
     * @return distance between two coordinates
     * */
    double getDistanceBetweenCoordinates(Coordinate c1, Coordinate c2);
}
