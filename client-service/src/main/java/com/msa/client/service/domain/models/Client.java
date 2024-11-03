package com.msa.client.service.domain.models;

import com.msa.client.service.domain.exceptions.ClientServiceException;
import com.msa.client.service.domain.utils.EncryptUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Client extends Person{

    private String password;

    private Integer status;

    public void encryptPassword(){
        this.setPassword(EncryptUtil.encryptPassword(getPassword()));
    }

    public void deactivateUser(){
        this.setStatus(0);
    }

    public void updateClient(Client newClient) {
        if (newClient == null) {
            throw new ClientServiceException("No existe nuevo cliente para actualizar");
        }
        updateIfDifferent(this::getName, this::setName, newClient.getName());
        updateIfDifferent(this::getIdentification, this::setIdentification, newClient.getIdentification());
        updateIfDifferent(this::getAge, this::setAge, newClient.getAge());
        updateIfDifferent(this::getGenre, this::setGenre, newClient.getGenre());
        updateIfDifferent(this::getDirection, this::setDirection, newClient.getDirection());
        updateIfDifferent(this::getPhone, this::setPhone, newClient.getPhone());
        updateIfDifferent(this::getPassword, this::setPassword, newClient.getPassword());
        updateIfDifferent(this::getStatus, this::setStatus, newClient.getStatus());
    }

    private <T> void updateIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        T currentValue = getter.get();
        if ((currentValue == null && newValue != null) ||
                (currentValue != null && !currentValue.equals(newValue))) {
            setter.accept(newValue);
        }
    }
}
