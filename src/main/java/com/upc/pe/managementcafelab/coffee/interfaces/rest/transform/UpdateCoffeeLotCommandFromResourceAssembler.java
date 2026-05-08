package com.upc.pe.managementcafelab.coffee.interfaces.rest.transform;

import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.Certification;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.ProcessingMethod;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotResource;

import java.util.stream.Collectors;

public class UpdateCoffeeLotCommandFromResourceAssembler {

    public static UpdateCoffeeLotCommand toCommand(
            Long coffeeLotId,
            UpdateCoffeeLotResource resource
    ) {

        return new UpdateCoffeeLotCommand(
                coffeeLotId,
                resource.supplierId(),
                resource.userId(),
                resource.lotName(),
                new CoffeeType(resource.coffeeType()),
                resource.origin(),
                resource.altitudeMeters(),
                new ProcessingMethod(resource.processingMethod()),
                resource.certifications()
                        .stream()
                        .map(Certification::new)
                        .collect(Collectors.toList())
        );
    }
}