package com.upc.pe.managementcafelab.inventory.application.internal.commandservices;

import com.upc.pe.managementcafelab.inventory.domain.model.aggregates.InventoryEntry;
import com.upc.pe.managementcafelab.inventory.domain.model.commands.CreateInventoryEntryCommand;
import com.upc.pe.managementcafelab.inventory.domain.services.InventoryEntryCommandService;
import com.upc.pe.managementcafelab.inventory.infrastructure.persistence.jpa.repositories.InventoryEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryEntryCommandServiceImpl implements InventoryEntryCommandService {

    private final InventoryEntryRepository repository;

    public InventoryEntryCommandServiceImpl(InventoryEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Optional<InventoryEntry> handle(CreateInventoryEntryCommand command) {
        var entry = new InventoryEntry(command);
        return Optional.of(repository.save(entry));
    }
}
