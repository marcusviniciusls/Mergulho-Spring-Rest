package com.algaworks.deliveredapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Destiny extends SuperEntity{

    private String name;
    private String address;
    private String number;
    private String complement;
    private String district;
}
