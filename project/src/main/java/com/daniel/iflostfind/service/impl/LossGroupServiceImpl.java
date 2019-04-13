package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.LossGroup;
import com.daniel.iflostfind.service.LossGroupService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class LossGroupServiceImpl implements LossGroupService {

    @Override
    public Set<String> getLossGroupNames() {
        return Arrays.stream(LossGroup.values())
                .map(LossGroup::getDisplayName)
                .collect(toSet());
    }

    @Override
    public Optional<LossGroup> getLossGroup(String name) {
        try {
            return Optional.of(LossGroup.valueOf(name.toUpperCase()));
        } catch (IllegalArgumentException e) {
            //TODO log
            return Optional.empty();
        }
    }
}
