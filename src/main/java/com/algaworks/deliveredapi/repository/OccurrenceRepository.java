package com.algaworks.deliveredapi.repository;

import com.algaworks.deliveredapi.model.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, UUID> {
}
