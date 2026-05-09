package com.upc.pe.managementcafelab.supplier.interfaces.rest.transform;

import com.upc.pe.managementcafelab.supplier.domain.model.commands.UpdateSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.resources.UpdateSupplierResource;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UpdateSupplierCommandFromResourceAssembler {

    public static UpdateSupplierCommand toCommandFromResource(
            Long supplierId,
            UpdateSupplierResource resource
    ) {

        return new UpdateSupplierCommand(
                supplierId,
                resource.name(),
                resource.email(),
                resource.phone(),
                resource.location(),
                resource.status(),
                resource.specialities()
                        .stream()
                        .map(SupplierSpeciality::new)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }
}