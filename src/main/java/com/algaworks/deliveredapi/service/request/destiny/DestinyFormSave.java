package com.algaworks.deliveredapi.service.request.destiny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DestinyFormSave {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String number;
    private String complement;
    @NotBlank
    private String district;

}
