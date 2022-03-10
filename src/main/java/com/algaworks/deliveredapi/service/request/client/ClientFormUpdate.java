package com.algaworks.deliveredapi.service.request.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientFormUpdate {

    private String name;
    private String email;
    private String telephone;
}
