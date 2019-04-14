package com.daniel.iflostfind.service;

import com.daniel.iflostfind.domain.FindingGroup;

import java.util.Optional;
import java.util.Set;

public interface LossGroupService {
    Set<String> getLossGroupNames();

    Optional<FindingGroup> getLossGroup(String name);
}
