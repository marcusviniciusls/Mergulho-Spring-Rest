package com.algaworks.deliveredapi.service.factory;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.model.Delivery;
import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.model.enums.DeliveryStatus;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import com.algaworks.deliveredapi.service.response.ClientDto;
import com.algaworks.deliveredapi.service.response.DeliveryDto;
import com.algaworks.deliveredapi.service.response.DestinyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryBusinessRule {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ClientBusinessRule clientBusinessRule;

    @Autowired
    private DestinyBusinessRule destinyBusinessRule;

    public Delivery convertSaveDeliveryFormInDelivery(SaveDeliveryForm saveDeliveryForm, Client client, Destiny destiny){
        Delivery delivery = new Delivery();

        delivery.setClient(client);
        delivery.setDestiny(destiny);
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);
        delivery.setTaxa(saveDeliveryForm.getPrice());
        delivery.setDateOrder(LocalDateTime.now());

        return delivery;
    }

    public DeliveryDto convertDeliveryInDeliveryDto(Delivery delivery){
        DeliveryDto deliveryDto = modelMapper.map(delivery ,DeliveryDto.class);
        ClientDto clientDto = clientBusinessRule.convertClientInClientDto(delivery.getClient());
        deliveryDto.setClientDto(clientDto);
        DestinyDto destinyDto = destinyBusinessRule.convertDestinyInDestinyDto(delivery.getDestiny());
        deliveryDto.setDestinyDto(destinyDto);
        return deliveryDto;
    }
}
