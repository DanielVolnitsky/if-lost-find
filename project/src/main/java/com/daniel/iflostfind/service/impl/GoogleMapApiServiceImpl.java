package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.service.GoogleMapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapApiServiceImpl implements GoogleMapApiService {

    private String googleMapKey;

    @Autowired
    public GoogleMapApiServiceImpl(@Qualifier("googleMapKey") String googleMapKey) {
        this.googleMapKey = googleMapKey;
    }

    @Override
    public String getMapKey() {
        return googleMapKey;
    }
}
