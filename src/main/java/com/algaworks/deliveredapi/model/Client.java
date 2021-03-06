package com.algaworks.deliveredapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Client extends SuperEntity{

    private String name;
    @Column(unique=true)
    private String email;
    private String telephone;
}
