package com.algaworks.deliveredapi.controller;

import com.algaworks.deliveredapi.service.ClientService;
import com.algaworks.deliveredapi.service.response.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value="client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable UUID id){
        ClientDto clientDto = clientService.findById(id);
        return ResponseEntity.ok().body(clientDto);
    }
}
