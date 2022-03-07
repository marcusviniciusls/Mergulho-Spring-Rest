package com.algaworks.deliveredapi.repository;

import com.algaworks.deliveredapi.model.Destiny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DestinyRepository extends JpaRepository<Destiny, UUID> {
}
