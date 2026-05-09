package com.upc.pe.managementcafelab.coffee.interfaces.rest.transform;

import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotStatusCommand;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotStatusResource;

public class UpdateCoffeeLotStatusCommandFromResourceAssembler {

    public static UpdateCoffeeLotStatusCommand toCommand(
            Long coffeeLotId,
            UpdateCoffeeLotStatusResource resource
    ) {

        return new UpdateCoffeeLotStatusCommand(
                coffeeLotId,
                resource.status()
        );
    }
}