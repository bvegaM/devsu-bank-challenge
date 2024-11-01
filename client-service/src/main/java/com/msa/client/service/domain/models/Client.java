package com.msa.client.service.domain.models;

import com.msa.client.service.domain.utils.EncryptUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}
