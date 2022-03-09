package com.algaworks.deliveredapi.controller;

import com.algaworks.deliveredapi.service.ClientService;
import com.algaworks.deliveredapi.service.request.ClientFormUpdate;
import com.algaworks.deliveredapi.service.response.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable){
        Page<ClientDto> pageClientDto = clientService.findAll(pageable);
        return ResponseEntity.ok().body(pageClientDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable UUID id, @RequestBody ClientFormUpdate clientFormUpdate){
        ClientDto clientDto = clientService.updateClient(id, clientFormUpdate);
        return ResponseEntity.noContent().build();
    }
}
