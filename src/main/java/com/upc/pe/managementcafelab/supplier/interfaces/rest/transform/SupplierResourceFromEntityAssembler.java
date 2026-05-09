package com.upc.pe.managementcafelab.supplier.interfaces.rest.transform;

import com.upc.pe.managementcafelab.supplier.domain.model.aggregates.Supplier;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.resources.SupplierResource;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SupplierResourceFromEntityAssembler {

    public static SupplierResource toResourceFromEntity(
            Supplier entity
    ) {

        return new SupplierResource(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getEmail().value(),
                entity.getPhone(),
                entity.getLocation(),
                entity.getStatus().toString(),
                entity.getSpecialities()
                        .stream()
                        .map(SupplierSpeciality::value)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }
}