package com.daniel.iflostfind.service.converter;

import java.util.Collection;
import java.util.List;

public interface EntityToDtoCollectionConverter<E, D> {
    List<D> convertEntitiesToDtos(Collection<E> e);
}
