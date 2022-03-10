package com.algaworks.deliveredapi.repository;

import com.algaworks.deliveredapi.model.Client;
import com.algaworks.deliveredapi.model.Destiny;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DestinyRepository extends JpaRepository<Destiny, UUID> {

    @Query("SELECT d FROM Destiny d WHERE d.status = true")
    Page<Destiny> findByAllNotExcluded(Pageable pageable);

    @Query("SELECT d FROM Destiny d WHERE d.status = true AND d.cep = :cep AND d.number = :number")
    Optional<Destiny> findByDestinyCepAndNumberUnico(@Param("cep") String cep, @Param("number")  String number);
}
