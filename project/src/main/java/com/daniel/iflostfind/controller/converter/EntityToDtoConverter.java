package com.daniel.iflostfind.controller.converter;

public interface EntityToDtoConverter<E, D> {
    D convertEntityToDto(E e);
}
