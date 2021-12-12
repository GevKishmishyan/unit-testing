package com.epam.unittesting.mapper;

public interface Mapper<T, V> {

    V mapToDTO(T t);

    T mapToOBJ(V v);

}
