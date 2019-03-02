package com.daniel.iflostfind.controller.converter;

import java.util.Collection;

public interface EntityToDtoCollectionConverter<E, D> {
    Collection<D> convertEntitiesToDtos(Collection<E> e);
}
