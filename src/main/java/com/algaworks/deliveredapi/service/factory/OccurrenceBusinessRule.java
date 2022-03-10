package com.algaworks.deliveredapi.service.factory;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.model.Occurrence;
import com.algaworks.deliveredapi.service.response.ClientDto;
import com.algaworks.deliveredapi.service.response.OccurrenceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OccurrenceBusinessRule {

    private ModelMapper modelMapper = new ModelMapper();

    public OccurrenceDto convertOccurrenceInOccurrenceDto(Occurrence occurrence){
        OccurrenceDto occurrenceDto = modelMapper.map(occurrence, OccurrenceDto.class);
        return occurrenceDto;
    }
}
