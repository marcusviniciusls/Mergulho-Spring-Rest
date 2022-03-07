package com.algaworks.deliveredapi.model;

import com.algaworks.deliveredapi.model.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery extends SuperEntity{

    private BigDecimal taxa = BigDecimal.ZERO;
    private LocalDateTime dateOrder;
    private LocalDateTime dateEnd;

    @Enumerated(EnumType.ORDINAL)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "destiny_id")
    private Destiny destiny;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> listOccurrence = new ArrayList<>();

    public void addListOccurrence(Occurrence occurrence){
        this.listOccurrence.add(occurrence);
    }
}
