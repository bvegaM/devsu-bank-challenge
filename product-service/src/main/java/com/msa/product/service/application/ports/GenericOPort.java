package com.msa.product.service.application.ports;

public interface GenericOPort<T, K>{

    T save(T body);

    T getById(K id);

    T updateById(T newBody);

    void deleteById(T body);
}
