package com.daniel.iflostfind.service.converter;

import java.util.Collection;

public interface EntityToDtoCollectionConverter<E, D> {
    Collection<D> convertEntitiesToDtos(Collection<E> e);
}
