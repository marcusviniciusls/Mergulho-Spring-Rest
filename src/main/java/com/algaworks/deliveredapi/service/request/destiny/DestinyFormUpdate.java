package com.algaworks.deliveredapi.service.request.destiny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DestinyFormUpdate {

    private String name;
    private String address;
    private String number;
    private String complement;
    private String district;
}
