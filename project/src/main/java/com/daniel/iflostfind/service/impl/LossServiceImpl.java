package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.Coordinate;
import com.daniel.iflostfind.domain.Loss;
import com.daniel.iflostfind.repository.LossRepository;
import com.daniel.iflostfind.service.LossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LossServiceImpl implements LossService {

    private final LossRepository lossRepository;

    @Autowired
    public LossServiceImpl(LossRepository lossRepository) {
        this.lossRepository = lossRepository;
    }

    @Override
    public void add(Loss loss) {
        lossRepository.save(loss);
    }

    @Override
    public List<Loss> getAll() {
        return (List<Loss>) lossRepository.findAll();
    }

    //TODO optimize
    @Override
    public List<Loss> getAllWithinRadiusOfCoordinate(Coordinate pivot, int radius) {
        List<Loss> all = getAll();
        return getFilteredNearbyLosses(pivot, radius, all);
    }

    private List<Loss> getFilteredNearbyLosses(Coordinate pivot, int radius, List<Loss> all) {
        return all.stream()
                .filter(l -> isCoordinateWithinRadiusOfAnother(pivot, l.getCoordinate(), radius))
                .collect(toList());
    }

    private boolean isCoordinateWithinRadiusOfAnother(Coordinate pivot, Coordinate other, int radiusKm) {

        double ky = (double) 40000 / 360;
        double kx = Math.cos(Math.PI * pivot.getLatitude() / 180.0) * ky;
        double dx = Math.abs(pivot.getLongitude() - other.getLongitude()) * kx;
        double dy = Math.abs(pivot.getLatitude() - other.getLatitude()) * ky;

        return Math.sqrt(dx * dx + dy * dy) <= radiusKm;
    }
}
