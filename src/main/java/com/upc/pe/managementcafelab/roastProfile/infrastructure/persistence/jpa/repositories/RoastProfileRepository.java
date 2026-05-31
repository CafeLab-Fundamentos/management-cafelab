package com.upc.pe.managementcafelab.roastProfile.infrastructure.persistence.jpa.repositories;

import com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates.RoastProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoastProfileRepository extends JpaRepository<RoastProfile, Long> {

    List<RoastProfile> findByCoffeeLotId(Long coffeeLotId);

    List<RoastProfile> findByUserId(Long userId);

    List<RoastProfile> findByIsFavorite(Boolean isFavorite);
}