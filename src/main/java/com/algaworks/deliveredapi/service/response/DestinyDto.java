package com.algaworks.deliveredapi.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DestinyDto {

    private String name;
    private String address;
    private String number;
    private String complement;
    private String district;

    public String getFullAddress(){
        return "Address: " + this.address + " Number: " + this.number + " Complement: " +
                this.complement + " District: " + this.district;
    }
}
