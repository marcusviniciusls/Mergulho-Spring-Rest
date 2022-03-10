package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.exception.ResourceNotFoundException;
import com.algaworks.deliveredapi.exception.StateDeliveyException;
import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.model.Delivery;
import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.model.enums.DeliveryStatus;
import com.algaworks.deliveredapi.repository.DeliveryRepository;
import com.algaworks.deliveredapi.repository.DestinyRepository;
import com.algaworks.deliveredapi.service.factory.DeliveryBusinessRule;
import com.algaworks.deliveredapi.service.request.delivery.SaveDeliveryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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

    @Transactional
    public void finishDelivery(UUID uuid){
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(uuid);
        checkDelivery(optionalDelivery);
        Delivery delivery = optionalDelivery.get();
        checkStatusForFinishOrCancel(optionalDelivery);
        delivery.setDeliveryStatus(DeliveryStatus.FINISH);
        delivery.setDateEnd(LocalDateTime.now());
        delivery.setDateUpdate(LocalDateTime.now());
        deliveryRepository.save(delivery);
        occurrenceService.saveOccurrence("Delivery Finish ", delivery);
    }

    @Transactional
    public void cancelDelivery(UUID uuid){
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(uuid);
        checkDelivery(optionalDelivery);
        checkStatusForFinishOrCancel(optionalDelivery);
        Delivery delivery = optionalDelivery.get();
        delivery.setDeliveryStatus(DeliveryStatus.CANCEL);
        delivery.setDateEnd(LocalDateTime.now());
        delivery.setDateUpdate(LocalDateTime.now());
        deliveryRepository.save(delivery);
        occurrenceService.saveOccurrence("Delivery Cancel ", delivery);
    }

    private void checkDelivery(Optional<Delivery> optionalDelivery){
        if(optionalDelivery.isEmpty() || optionalDelivery.get().isStatus() == false){
            throw new ResourceNotFoundException("Delivery Not Found");
        }
    }

    private void checkStatusForFinishOrCancel(Optional<Delivery> optionalDelivery){
        if (optionalDelivery.get().getDeliveryStatus().equals(DeliveryStatus.FINISH) || optionalDelivery.get().getDeliveryStatus().equals(DeliveryStatus.CANCEL)){
            throw new StateDeliveyException("Unable to change status to finish");
        }
    }
}
