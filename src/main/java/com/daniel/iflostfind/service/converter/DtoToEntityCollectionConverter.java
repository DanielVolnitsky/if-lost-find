package com.daniel.iflostfind.service.converter;

import java.util.Collection;

public interface DtoToEntityCollectionConverter<E, D> {
    Collection<E> convertDtosToEntities(Collection<D> d);
}
