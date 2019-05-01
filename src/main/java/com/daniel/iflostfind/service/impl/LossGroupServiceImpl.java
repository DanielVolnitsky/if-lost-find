package com.daniel.iflostfind.service.impl;

import com.daniel.iflostfind.domain.FindingGroup;
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
        return Arrays.stream(FindingGroup.values())
                .map(FindingGroup::getDisplayName)
                .collect(toSet());
    }

    @Override
    public Optional<FindingGroup> getLossGroup(String name) {
        try {
            return Optional.of(FindingGroup.valueOf(name.toUpperCase()));
        } catch (IllegalArgumentException e) {
            //TODO log
            return Optional.empty();
        }
    }
}
