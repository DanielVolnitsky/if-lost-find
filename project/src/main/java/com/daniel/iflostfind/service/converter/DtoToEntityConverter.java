package com.daniel.iflostfind.service.converter;

public interface DtoToEntityConverter<E, D> {
    E convertDtoToEntity(D d);
}
