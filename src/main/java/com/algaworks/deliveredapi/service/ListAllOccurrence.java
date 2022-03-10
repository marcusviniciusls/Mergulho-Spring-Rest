package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.model.Occurrence;
import com.algaworks.deliveredapi.repository.OccurrenceRepository;
import com.algaworks.deliveredapi.service.factory.OccurrenceBusinessRule;
import com.algaworks.deliveredapi.service.response.OccurrenceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListAllOccurrence {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private OccurrenceBusinessRule occurrenceBusinessRule;

    public Page<OccurrenceDto> findAllOccurrence(Pageable pageable){
        Page<Occurrence> pageOccurrence = occurrenceRepository.findByOccurence(pageable);
        Page<OccurrenceDto> pageOccurrenceDto = pageOccurrence.map(occurrence -> occurrenceBusinessRule.convertOccurrenceInOccurrenceDto(occurrence));
        return pageOccurrenceDto;
    }
}
