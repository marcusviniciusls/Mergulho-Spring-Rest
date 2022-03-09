package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.exception.AlredyEmailExist;
import com.algaworks.deliveredapi.exception.ResourceNotFoundException;
import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.service.factory.ClientBusinessRule;
import com.algaworks.deliveredapi.service.request.ClientFormSave;
import com.algaworks.deliveredapi.service.request.ClientFormUpdate;
import com.algaworks.deliveredapi.service.response.ClientDto;
import com.algaworks.deliveredapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Page<ClientDto> findAll(Pageable pageable){
        Page<Client> pageClient = clientRepository.findByAllNotExcluded(pageable);
        Page<ClientDto> pageClientDto = pageClient.map(client -> clientBusinessRule.convertClientInClientDto(client));
        return pageClientDto;
    }

    private void verifyCheckedClient(Optional<Client> clientOptional){
        if (clientOptional.isEmpty() || clientOptional.get().isStatus() == false){
            throw new ResourceNotFoundException("Client Not Found");
        }
    }

    public ClientDto updateClient(UUID uuid, ClientFormUpdate clientFormUpdate){
        Optional<Client> optionalClient = clientRepository.findById(uuid);
        verifyCheckedClient(optionalClient);
        Client client = optionalClient.get();
        client = clientBusinessRule.convertClientFormInClient(clientFormUpdate, client);
        client.setDateUpdate(LocalDateTime.now());
        client = clientRepository.save(client);
        ClientDto clientDto = clientBusinessRule.convertClientInClientDto(client);
        return clientDto;
    }

    public void deleteClient(UUID uuid){
        try{
            clientRepository.deleteById(uuid);
        } catch(DataIntegrityViolationException dataIntegrityViolationException){
            Client client = clientRepository.findById(uuid).get();
            client = markedDeleteClient(client);
            clientRepository.save(client);
        }
    }

    private Client markedDeleteClient(Client client){
        client.setStatus(false);
        return client;
    }

    public void saveClient(ClientFormSave clientFormSave){
        findByClientEmail(clientFormSave.getEmail());
        Client client = clientBusinessRule.convertClientFromSaveInClient(clientFormSave);
        clientRepository.save(client);
    }

    private void findByClientEmail(String email){
        Optional<Client> optionalClient = clientRepository.findByByEmailNotExcluded(email);
        if (optionalClient.isPresent()){
            throw new AlredyEmailExist("E-MAIL ALREDY EXIST");
        }
    }
}
