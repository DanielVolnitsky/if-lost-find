package com.daniel.iflostfind.converter;

public interface DtoConverter<E, D> {
    D convert(E e);
}
