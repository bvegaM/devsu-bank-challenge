package com.msa.client.service.infrastructure.adapters.out.client.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "T_CLIENTS")
public class ClientEntity extends PersonEntity{

    @Column(name = "CLI_PASSWORD", nullable = false)
    private String password;

    @Column(name = "CLI_STATUS")
    private String status;
}
