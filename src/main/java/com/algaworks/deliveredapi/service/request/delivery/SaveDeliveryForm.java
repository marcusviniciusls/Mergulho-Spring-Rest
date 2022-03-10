package com.algaworks.deliveredapi.service.request.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveDeliveryForm {

    @NotBlank
    private String emailClient;
    @NotBlank
    private String cep;
    @NotBlank
    private String number;
    @NotBlank
    private BigDecimal price;
    private String description;
}
