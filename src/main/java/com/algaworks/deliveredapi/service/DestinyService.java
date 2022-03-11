package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.exception.AddressAlredyExistException;
import com.algaworks.deliveredapi.exception.ResourceNotFoundException;
import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.repository.DestinyRepository;
import com.algaworks.deliveredapi.service.factory.DestinyBusinessRule;
import com.algaworks.deliveredapi.service.request.destiny.DestinyFormSave;
import com.algaworks.deliveredapi.service.request.destiny.DestinyFormUpdate;
import com.algaworks.deliveredapi.service.response.DestinyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DestinyService {

    @Autowired
    private DestinyRepository destinyRepository;

    @Autowired
    private DestinyBusinessRule destinyBusinessRule;

    public DestinyDto findById(UUID uuid){
        Optional<Destiny> optionalDestiny = destinyRepository.findById(uuid);
        verifyCheckedDestiny(optionalDestiny);
        Destiny destiny = optionalDestiny.get();
        return destinyBusinessRule.convertDestinyInDestinyDto(destiny);
    }

    public void verifyCheckedDestiny(Optional<Destiny> optionalDestiny){
        if (optionalDestiny.isEmpty() || optionalDestiny.get().isStatus() == false){
            throw new ResourceNotFoundException("RESOURCE NOT FOUND EXCEPTION");
        }
    }

    public Page<DestinyDto> findAll(Pageable pageable){
        Page<Destiny> pageDestiny = destinyRepository.findByAllNotExcluded(pageable);
        Page<DestinyDto> pageDestinyDto = pageDestiny.map(destiny -> destinyBusinessRule.convertDestinyInDestinyDto(destiny));
        return pageDestinyDto;
    }

    public DestinyDto updateDestiny(UUID uuid, DestinyFormUpdate destinyFormUpdate){
        Optional<Destiny> optionalDestiny = destinyRepository.findById(uuid);
        verifyCheckedDestiny(optionalDestiny);
        Destiny destiny = optionalDestiny.get();
        destiny = destinyBusinessRule.updateDestiny(destiny, destinyFormUpdate);
        destiny.setDateUpdate(LocalDateTime.now());
        destiny = destinyRepository.save(destiny);
        DestinyDto destinyDto = destinyBusinessRule.convertDestinyInDestinyDto(destiny);
        return destinyDto;
    }

    public void deleteDestiny(UUID uuid){
        try{
            destinyRepository.deleteById(uuid);
        } catch(DataIntegrityViolationException dataIntegrityViolationException){
            Destiny destiny = destinyRepository.findById(uuid).get();
            destiny = markedDeleteDestiny(destiny);
            destinyRepository.save(destiny);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new ResourceNotFoundException("ID NOT FOUND EXCEPTION");
        }
    }

    private Destiny markedDeleteDestiny(Destiny destiny){
        destiny.setStatus(false);
        return destiny;
    }

    public void saveDestiny(DestinyFormSave destinyFormSave){
        checkAddressUnique(destinyFormSave.getCep(), destinyFormSave.getNumber());
        Destiny destiny = destinyBusinessRule.convertDestinyFormSaveInDestiny(destinyFormSave);
        destinyRepository.save(destiny);
    }

    private void checkAddressUnique(String cep, String number){
        Optional<Destiny> optionalDestiny = destinyRepository.findByDestinyCepAndNumberUnico(cep, number);
        if (optionalDestiny.isPresent()){
            throw new AddressAlredyExistException("Address Alredy Exist");
        }
    }
}
