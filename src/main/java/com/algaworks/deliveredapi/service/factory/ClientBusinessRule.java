package com.algaworks.deliveredapi.service.factory;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.service.request.ClientFormUpdate;
import com.algaworks.deliveredapi.service.response.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientBusinessRule {

    private ModelMapper modelMapper = new ModelMapper();

    public ClientDto convertClientInClientDto(Client client){
        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        return clientDto;
    }

    public Client convertClientFormInClient(ClientFormUpdate clientFormUpdate, Client client){
        if (clientFormUpdate.getEmail() != null){
            client.setEmail(clientFormUpdate.getEmail());
        }
        if (clientFormUpdate.getName() != null){
            client.setName(clientFormUpdate.getName());
        }
        if (clientFormUpdate.getTelephone() != null){
            client.setTelephone(clientFormUpdate.getTelephone());
        }
        return client;
    }
}
