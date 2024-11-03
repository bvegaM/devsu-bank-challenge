package com.msa.product.service.infrastructure.adapters.out.client.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonEntity {

    private Integer id;

    private String name;

    private String identification;

    private Integer age;

    private String genre;

    private String direction;

    private String phone;
}
