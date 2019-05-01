package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.service.GoogleMapKeyService;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapKeyOSEnvService implements GoogleMapKeyService {

    @Override
    public String getMapKey() {
        return System.getenv("google-maps-api");
    }
}
