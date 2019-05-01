package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.service.GoogleMapService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

@Service
public class GoogleMapServiceImpl implements GoogleMapService {

    @Value("classpath:hidden/google_maps_api_key")
    private Resource googleMapKeyResource;

    @SneakyThrows
    @Override
    public String getMapKey() {
        File file = googleMapKeyResource.getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }
}
