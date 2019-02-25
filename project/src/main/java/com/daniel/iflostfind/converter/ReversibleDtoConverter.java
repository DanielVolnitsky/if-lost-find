package com.daniel.iflostfind.converter;

public interface ReversibleDtoConverter<E, D> extends DtoConverter<E, D>
{
    E reverseConvert(D dto);
}
