package com.upc.pe.managementcafelab.supplier.domain.model.commands;

public record DeleteSupplierCommand (Long id){
    public DeleteSupplierCommand {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("id es requerido y debe ser positivo");
    }
}
