package com.upc.pe.managementcafelab.supplier.domain.services;

import com.upc.pe.managementcafelab.supplier.domain.model.aggregates.Supplier;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.CreateSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.DeleteSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.UpdateSupplierCommand;

import java.util.Optional;

public interface SupplierCommandService {

    Optional<Supplier> handle(CreateSupplierCommand command);

    Optional<Supplier> handle(UpdateSupplierCommand command);

    void handle(DeleteSupplierCommand command);
}