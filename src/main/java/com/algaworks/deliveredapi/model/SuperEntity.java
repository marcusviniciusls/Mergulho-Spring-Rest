package com.algaworks.deliveredapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class SuperEntity {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID uuid;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private boolean status;

    public SuperEntity() {
        uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        dateCreation = now;
        status = true;
    }

    public boolean isStatus() {
        return status;
    }
}