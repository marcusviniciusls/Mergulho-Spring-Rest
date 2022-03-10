package com.algaworks.deliveredapi.service.factory;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.model.Delivery;
import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.model.enums.DeliveryStatus;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryBusinessRule {

    public Delivery convertSaveDeliveryFormInDelivery(SaveDeliveryForm saveDeliveryForm, Client client, Destiny destiny){
        Delivery delivery = new Delivery();

        delivery.setClient(client);
        delivery.setDestiny(destiny);
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);
        delivery.setTaxa(saveDeliveryForm.getPrice());
        delivery.setDateOrder(LocalDateTime.now());

        return delivery;
    }
}
