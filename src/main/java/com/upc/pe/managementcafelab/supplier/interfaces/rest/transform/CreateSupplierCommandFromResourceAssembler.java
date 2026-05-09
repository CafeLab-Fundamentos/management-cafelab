package com.upc.pe.managementcafelab.supplier.interfaces.rest.transform;

import com.upc.pe.managementcafelab.supplier.domain.model.commands.CreateSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.resources.CreateSupplierResource;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CreateSupplierCommandFromResourceAssembler {

    public static CreateSupplierCommand toCommandFromResource(
            CreateSupplierResource resource
    ) {

        return new CreateSupplierCommand(
                resource.userId(),
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