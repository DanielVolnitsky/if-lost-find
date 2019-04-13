package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.LossType;
import com.daniel.iflostfind.service.LossGroupService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class LossGroupServiceImpl implements LossGroupService {

    @Override
    public Set<String> getLossGroupNames() {
        return Arrays.stream(LossType.values())
                .map(LossType::getDisplayName)
                .collect(toSet());
    }
}
