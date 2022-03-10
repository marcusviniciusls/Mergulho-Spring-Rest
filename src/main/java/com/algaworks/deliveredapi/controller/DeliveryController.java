package com.algaworks.deliveredapi.controller;

import com.algaworks.deliveredapi.service.DeliveryService;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value="/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<?> saveDelivery(@RequestBody SaveDeliveryForm saveDeliveryForm){
        deliveryService.saveDelivery(saveDeliveryForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> finishDelivery(@PathVariable UUID id){
        deliveryService.finishDelivery(id);
        return ResponseEntity.noContent().build();
    }
}
