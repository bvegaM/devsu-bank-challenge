package com.msa.product.service.application.ports;

public interface GenericIPort<T, K>{

    T save(T body);

    T getById(K id);

    T updateById(K id, T newBody);

    void deleteById(K id);
}
