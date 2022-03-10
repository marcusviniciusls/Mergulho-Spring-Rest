package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.model.Delivery;
import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.repository.DeliveryRepository;
import com.algaworks.deliveredapi.repository.DestinyRepository;
import com.algaworks.deliveredapi.service.factory.DeliveryBusinessRule;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private DestinyRepository destinyRepository;

    @Autowired
    private DestinyService destinyService;

    @Autowired
    private DeliveryBusinessRule deliveryBusinessRule;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OccurrenceService occurrenceService;

    @Transactional
    public void saveDelivery(SaveDeliveryForm saveDeliveryForm){
        Client client = clientService.findClientByEmail(saveDeliveryForm.getEmailClient());
        Optional<Destiny> optionalDestiny = destinyRepository.findByDestinyCepAndNumberUnico(saveDeliveryForm.getCep(), saveDeliveryForm.getNumber());
        destinyService.verifyCheckedDestiny(optionalDestiny);
        Destiny destiny = optionalDestiny.get();
        Delivery delivery = deliveryBusinessRule.convertSaveDeliveryFormInDelivery(saveDeliveryForm,client,destiny);
        deliveryRepository.save(delivery);
        occurrenceService.saveOccurrence("DELIVERY SAVE " + saveDeliveryForm.getDescription(), delivery);
    }
}
