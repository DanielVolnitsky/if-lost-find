package com.daniel.iflostfind.controller.converter;

public interface DtoToEntityConverter<E, D> {
    E convertDtoToEntity(D d);
}
