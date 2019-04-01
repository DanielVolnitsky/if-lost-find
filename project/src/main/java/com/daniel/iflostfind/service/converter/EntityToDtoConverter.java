package com.daniel.iflostfind.service.converter;

public interface EntityToDtoConverter<E, D> {
    D convertEntityToDto(E e);
}
