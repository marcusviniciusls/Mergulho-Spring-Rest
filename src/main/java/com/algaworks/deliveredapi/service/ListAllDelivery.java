package com.algaworks.deliveredapi.service;

import com.algaworks.deliveredapi.model.Delivery;
import com.algaworks.deliveredapi.repository.DeliveryRepository;
import com.algaworks.deliveredapi.service.factory.DeliveryBusinessRule;
import com.algaworks.deliveredapi.service.response.DeliveryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllDelivery {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryBusinessRule deliveryBusinessRule;

    public Page<DeliveryDto> findAllDelivery(Pageable pageable){
        Page<Delivery> pageDelivery = deliveryRepository.findByAllNotExcluded(pageable);
        Page<DeliveryDto> pageDeliveryDto = pageDelivery.map(delivery -> deliveryBusinessRule.convertDeliveryInDeliveryDto(delivery));
        return pageDeliveryDto;
    }
}
