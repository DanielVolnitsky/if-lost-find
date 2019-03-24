package com.daniel.iflostfind.controller.converter;

import java.util.Collection;

public interface DtoToEntityCollectionConverter<E, D> {
    Collection<E> convertDtosToEntities(Collection<D> d);
}
