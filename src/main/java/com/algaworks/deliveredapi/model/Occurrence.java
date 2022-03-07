package com.algaworks.deliveredapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Occurrence extends SuperEntity{

    private String description;
    private LocalDateTime registrationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public void setDescription(String description) {
        this.description = description;
    }
}
