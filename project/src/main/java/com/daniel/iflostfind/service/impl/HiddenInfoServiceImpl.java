package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.service.HiddenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class HiddenInfoServiceImpl implements HiddenInfoService {

    private String googleMapKey;

    @Autowired
    public HiddenInfoServiceImpl(@Qualifier("googleMapKey") String googleMapKey) {
        this.googleMapKey = googleMapKey;
    }

    @Override
    public String getMapKey() {
        return googleMapKey;
    }
}
