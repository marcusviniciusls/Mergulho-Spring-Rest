package com.algaworks.deliveredapi.repository;

import com.algaworks.deliveredapi.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

    @Query("SELECT d FROM Delivery d WHERE d.status = true AND d.client.email = :email")
    Page<Delivery> findAllByClientEmail(Pageable pageable, @Param("email") String email);
}
