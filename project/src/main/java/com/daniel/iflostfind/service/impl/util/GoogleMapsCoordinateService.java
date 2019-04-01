package com.daniel.iflostfind.service.impl.util;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.service.util.CoordinateService;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsCoordinateService implements CoordinateService {

    @Override
    public double getDistanceBetweenCoordinates(Coordinate pivot, Coordinate other) {
        double ky = (double) 40000 / 360;
        double kx = Math.cos(Math.PI * pivot.getLatitude() / 180.0) * ky;
        double dx = Math.abs(pivot.getLongitude() - other.getLongitude()) * kx;
        double dy = Math.abs(pivot.getLatitude() - other.getLatitude()) * ky;

        return Math.sqrt(dx * dx + dy * dy);
    }
}
