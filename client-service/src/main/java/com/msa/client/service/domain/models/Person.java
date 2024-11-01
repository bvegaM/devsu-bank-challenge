package com.msa.client.service.domain.models;

import com.msa.client.service.domain.models.enums.GenreEnum;
import lombok.*;
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

    private GenreEnum genre;

    private String direction;

    private String phone;
}
