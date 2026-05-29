package com.upc.pe.managementcafelab.inventory.domain.services;

import com.upc.pe.managementcafelab.inventory.domain.model.aggregates.InventoryEntry;
import com.upc.pe.managementcafelab.inventory.domain.model.commands.CreateInventoryEntryCommand;

import java.util.Optional;

public interface InventoryEntryCommandService {
    Optional<InventoryEntry> handle(CreateInventoryEntryCommand command);
}
