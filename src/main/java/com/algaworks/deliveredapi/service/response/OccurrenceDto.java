package com.algaworks.deliveredapi.service.response;

import com.algaworks.deliveredapi.model.Delivery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceDto {

    private String description;
    private LocalDateTime registrationDate = LocalDateTime.now();
    @JsonIgnore
    private Delivery delivery;
}
