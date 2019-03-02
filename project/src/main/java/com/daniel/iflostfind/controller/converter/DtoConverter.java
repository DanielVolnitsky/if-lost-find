package com.daniel.iflostfind.controller.converter;

public interface DtoConverter<E, D> {
    D convert(E e);
}
