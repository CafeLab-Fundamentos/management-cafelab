package com.upc.pe.managementcafelab.inventory.domain.services;

import com.upc.pe.managementcafelab.inventory.domain.model.aggregates.InventoryEntry;
import com.upc.pe.managementcafelab.inventory.domain.model.queries.GetInventoryEntriesByUserIdQuery;

import java.util.List;

public interface InventoryEntryQueryService {
    List<InventoryEntry> handle(GetInventoryEntriesByUserIdQuery query);
}
