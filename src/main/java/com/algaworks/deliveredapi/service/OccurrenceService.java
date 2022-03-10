package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.model.Delivery;
import com.algaworks.deliveredapi.model.Occurrence;
import com.algaworks.deliveredapi.repository.OccurrenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OccurrenceService {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Transactional
    public void saveOccurrence(String description, Delivery delivery){
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setDelivery(delivery);
        occurrenceRepository.save(occurrence);
    }
}
