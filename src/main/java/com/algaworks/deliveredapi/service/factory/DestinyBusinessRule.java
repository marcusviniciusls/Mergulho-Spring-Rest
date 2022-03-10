package com.algaworks.deliveredapi.service.factory;

import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.service.request.destiny.DestinyFormSave;
import com.algaworks.deliveredapi.service.request.destiny.DestinyFormUpdate;
import com.algaworks.deliveredapi.service.response.DestinyDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DestinyBusinessRule {

    private ModelMapper modelMapper = new ModelMapper();

    public DestinyDto convertDestinyInDestinyDto(Destiny destiny){
        DestinyDto destinyDto = modelMapper.map(destiny, DestinyDto.class);
        return destinyDto;
    }

    public Destiny updateDestiny(Destiny destiny, DestinyFormUpdate destinyFormUpdate){
        if (destinyFormUpdate.getAddress() != null){
            destiny.setName(destinyFormUpdate.getAddress());
        }
        if (destinyFormUpdate.getName() != null){
            destiny.setName(destinyFormUpdate.getName());
        }
        if (destinyFormUpdate.getComplement() != null){
            destiny.setComplement(destinyFormUpdate.getComplement());
        }
        if (destinyFormUpdate.getDistrict() != null){
            destiny.setDistrict(destinyFormUpdate.getDistrict());
        }
        if (destinyFormUpdate.getNumber() != null){
            destiny.setNumber(destinyFormUpdate.getNumber());
        }
        return destiny;
    }

    public Destiny convertDestinyFormSaveInDestiny(DestinyFormSave destinyFormSave){
        Destiny destiny = modelMapper.map(destinyFormSave, Destiny.class);
        return destiny;
    }
}
