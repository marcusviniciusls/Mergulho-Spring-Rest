package com.algaworks.deliveredapi.controller;

import com.algaworks.deliveredapi.service.DeliveryService;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
