package com.msa.client.service.infrastructure.adapters.out.client.entity;

import com.msa.client.service.domain.models.enums.GenreEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_PERSONS")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "PER_DNI", unique = true, nullable = false)
    private String identification;

    @Column(name = "PER_NAME", nullable = false)
    private String name;

    @Column(name = "PER_AGE", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Column(name = "PER_ADDRESS", nullable = false)
    private String direction;

    @Column(name = "PER_PHONE", nullable = false)
    private String phone;
}
