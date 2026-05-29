package com.upc.pe.managementcafelab.inventory.application.internal.queryservices;

import com.upc.pe.managementcafelab.inventory.domain.model.aggregates.InventoryEntry;
import com.upc.pe.managementcafelab.inventory.domain.model.queries.GetInventoryEntriesByUserIdQuery;
import com.upc.pe.managementcafelab.inventory.domain.services.InventoryEntryQueryService;
import com.upc.pe.managementcafelab.inventory.infrastructure.persistence.jpa.repositories.InventoryEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryEntryQueryServiceImpl implements InventoryEntryQueryService {

    private final InventoryEntryRepository repository;

    public InventoryEntryQueryServiceImpl(InventoryEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InventoryEntry> handle(GetInventoryEntriesByUserIdQuery query) {
        return repository.findByUserIdOrderByDateUsedDesc(query.userId());
    }
}
