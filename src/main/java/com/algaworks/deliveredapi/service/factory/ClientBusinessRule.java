package com.algaworks.deliveredapi.service.factory;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.service.response.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ClientBusinessRule {

    private ModelMapper modelMapper = new ModelMapper();

    public ClientDto convertClientInClientDto(Client client){
        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        return clientDto;
    }
}
