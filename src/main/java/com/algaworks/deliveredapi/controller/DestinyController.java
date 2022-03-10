package com.algaworks.deliveredapi.controller;

import com.algaworks.deliveredapi.service.DestinyService;
import com.algaworks.deliveredapi.service.request.client.ClientFormSave;
import com.algaworks.deliveredapi.service.request.client.ClientFormUpdate;
import com.algaworks.deliveredapi.service.request.destiny.DestinyFormSave;
import com.algaworks.deliveredapi.service.request.destiny.DestinyFormUpdate;
import com.algaworks.deliveredapi.service.response.ClientDto;
import com.algaworks.deliveredapi.service.response.DestinyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value="destiny")
public class DestinyController {

    @Autowired
    private DestinyService destinyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DestinyDto> findById(@PathVariable UUID id){
        DestinyDto destinyDto = destinyService.findById(id);
        return ResponseEntity.ok().body(destinyDto);
    }

    @GetMapping
    public ResponseEntity<Page<DestinyDto>> findAll(Pageable pageable){
        Page<DestinyDto> pageDestinyDto = destinyService.findAll(pageable);
        return ResponseEntity.ok().body(pageDestinyDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> updateDestiny(@PathVariable UUID id, @Valid @RequestBody DestinyFormUpdate destinyFormUpdate){
        DestinyDto destinyDto = destinyService.updateDestiny(id, destinyFormUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDestiny(@PathVariable UUID id){
        destinyService.deleteDestiny(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<DestinyDto> saveDestiny(@Valid @RequestBody DestinyFormSave destinyFormSave){
        destinyService.saveDestiny(destinyFormSave);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
