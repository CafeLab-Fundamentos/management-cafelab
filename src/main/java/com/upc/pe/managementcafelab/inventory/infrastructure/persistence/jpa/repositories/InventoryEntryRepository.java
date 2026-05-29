package com.upc.pe.managementcafelab.inventory.infrastructure.persistence.jpa.repositories;

import com.upc.pe.managementcafelab.inventory.domain.model.aggregates.InventoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryEntryRepository extends JpaRepository<InventoryEntry, Long> {

    List<InventoryEntry> findByUserIdOrderByDateUsedDesc(Long userId);

    List<InventoryEntry> findByCoffeeLotIdOrderByDateUsedDesc(Long coffeeLotId);
}
