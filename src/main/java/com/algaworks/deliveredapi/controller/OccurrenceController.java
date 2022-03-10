package com.algaworks.deliveredapi.controller;

import com.algaworks.deliveredapi.service.AllDeliveryByClientService;
import com.algaworks.deliveredapi.service.DeliveryService;
import com.algaworks.deliveredapi.service.ListAllDelivery;
import com.algaworks.deliveredapi.service.ListAllOccurrence;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import com.algaworks.deliveredapi.service.response.DeliveryDto;
import com.algaworks.deliveredapi.service.response.OccurrenceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value="/occurrence")
public class OccurrenceController {

    @Autowired
    private ListAllOccurrence listAllOccurrence;

    @GetMapping
    public ResponseEntity<Page<OccurrenceDto>> findAllOccurence(Pageable pageable){
        Page<OccurrenceDto> pageOccurenceDto = listAllOccurrence.findAllOccurrence(pageable);
        return ResponseEntity.ok().body(pageOccurenceDto);
    }
}
