package com.algaworks.deliveredapi.repository;

import com.algaworks.deliveredapi.model.Destiny;
import com.algaworks.deliveredapi.model.Occurrence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, UUID> {

    @Query("SELECT o FROM Occurrence o WHERE o.status = true")
    Page<Occurrence> findByOccurence(Pageable pageable);
}
