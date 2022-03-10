package com.algaworks.deliveredapi.controller;

import com.algaworks.deliveredapi.service.AllDeliveryByClientService;
import com.algaworks.deliveredapi.service.DeliveryService;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import com.algaworks.deliveredapi.service.response.DeliveryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value="/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private AllDeliveryByClientService allDeliveryByClientService;

    @PostMapping
    public ResponseEntity<?> saveDelivery(@RequestBody SaveDeliveryForm saveDeliveryForm){
        deliveryService.saveDelivery(saveDeliveryForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/finish/{id}")
    public ResponseEntity<?> finishDelivery(@PathVariable UUID id){
        deliveryService.finishDelivery(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<?> cancelDelivery(@PathVariable UUID id){
        deliveryService.cancelDelivery(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{email}/client")
    public ResponseEntity<Page<DeliveryDto>> findByDeliveyByClient(Pageable pageable, @PathVariable String email){
        Page<DeliveryDto> pageDevliveryDto = allDeliveryByClientService.findByDeliveryByClient(pageable,email);
        return ResponseEntity.ok().body(pageDevliveryDto);
    }
}
