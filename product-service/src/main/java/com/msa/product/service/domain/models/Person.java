package com.msa.product.service.domain.models;

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
public class Person {

    private Integer id;

    private String name;

    private String identification;

    private Integer age;

    private String genre;

    private String direction;

    private String phone;
}
