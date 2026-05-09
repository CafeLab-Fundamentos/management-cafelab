package com.upc.pe.managementcafelab.supplier.application.internal.commandservices;

import com.upc.pe.managementcafelab.supplier.domain.model.aggregates.Supplier;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.CreateSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.DeleteSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.UpdateSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.services.SupplierCommandService;

import com.upc.pe.managementcafelab.supplier.infrastructure.persistance.jps.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SupplierCommandServiceImpl implements SupplierCommandService {

    private final SupplierRepository repository;

    public SupplierCommandServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Optional<Supplier> handle(CreateSupplierCommand command) {

        if (repository.existsByEmail_Value(command.email())) {
            throw new IllegalArgumentException("El email ya existe");
        }

        var supplier = new Supplier(command);

        return Optional.of(repository.save(supplier));
    }

    @Override
    @Transactional
    public Optional<Supplier> handle(UpdateSupplierCommand command) {

        return repository.findById(command.supplierId())
                .map(existingSupplier -> {

                    existingSupplier.update(command);

                    return repository.save(existingSupplier);
                });
    }

    @Override
    @Transactional
    public void handle(DeleteSupplierCommand command) {

        repository.findById(command.id())
                .ifPresent(repository::delete);
    }
}