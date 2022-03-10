package com.algaworks.deliveredapi.service.response;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.model.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private BigDecimal taxa;
    private LocalDateTime dateOrder;
    private LocalDateTime dateEnd;
    private Integer status;
    private ClientDto clientDto;
    private DestinyDto destinyDto;
}
