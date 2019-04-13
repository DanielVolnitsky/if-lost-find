package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.LossGroup;

import java.util.Optional;
import java.util.Set;

public interface LossGroupService {
    Set<String> getLossGroupNames();

    Optional<LossGroup> getLossGroup(String name);
}
