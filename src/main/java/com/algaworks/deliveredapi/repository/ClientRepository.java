package com.algaworks.deliveredapi.repository;

import com.algaworks.deliveredapi.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT c FROM Client c WHERE c.status = true")
    Page<Client> findByAllNotExcluded(Pageable pageable);

    @Query("SELECT c FROM Client c WHERE c.status = true AND c.email = :email")
    Optional<Client> findByByEmailNotExcluded(@Param("email") String email);
}
