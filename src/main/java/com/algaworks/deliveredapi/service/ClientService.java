package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.exception.ResourceNotFoundException;
import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.service.factory.ClientBusinessRule;
import com.algaworks.deliveredapi.service.response.ClientDto;
import com.algaworks.deliveredapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientBusinessRule clientBusinessRule;

    public ClientDto findById(UUID uuid){
        Optional<Client> clientOptional = clientRepository.findById(uuid);
        verifyCheckedClient(clientOptional);
        Client client = clientOptional.get();
        ClientDto clientDto = clientBusinessRule.convertClientInClientDto(client);
        return clientDto;
    }

    private void verifyCheckedClient(Optional<Client> clientOptional){
        if (clientOptional.isEmpty() || clientOptional.get().isStatus() == false){
            throw new ResourceNotFoundException("Client Not Found");
        }
    }
}
