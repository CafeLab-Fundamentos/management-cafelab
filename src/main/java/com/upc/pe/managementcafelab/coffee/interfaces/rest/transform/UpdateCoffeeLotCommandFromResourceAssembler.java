package com.upc.pe.managementcafelab.coffee.interfaces.rest.transform;


import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotResource;

public class UpdateCoffeeLotCommandFromResourceAssembler {

    public static UpdateCoffeeLotCommand toCommand(
            Long coffeeLotId,
            UpdateCoffeeLotResource resource
    ) {

        return new UpdateCoffeeLotCommand(
                coffeeLotId,
                resource.supplierId(),
                resource.lotName(),
                resource.coffeeType(),
                resource.origin(),
                resource.altitudeMeters(),
                resource.processingMethod(),
                resource.certifications()
        );
    }
}