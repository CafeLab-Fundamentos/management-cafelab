package com.upc.pe.managementcafelab.coffee.domain.model.queries;

public record GetCoffeeLotsBySupplierIdQuery(Long supplierId) {
    public GetCoffeeLotsBySupplierIdQuery {
        if (supplierId == null || supplierId <= 0) {
            throw new IllegalArgumentException("supplierId es requerido");
        }
    }
}