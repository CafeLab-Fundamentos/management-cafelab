package com.upc.pe.managementcafelab.coffee.interfaces.rest.transform;

import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotRemainingWeightCommand;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotRemainingWeightResource;

public class UpdateCoffeeLotRemainingWeightCommandFromResourceAssembler {
    public static UpdateCoffeeLotRemainingWeightCommand toCommand(
            Long coffeeLotId,
            UpdateCoffeeLotRemainingWeightResource resource
    ) {

        return new UpdateCoffeeLotRemainingWeightCommand(
                coffeeLotId,
                resource.weight()
        );
    }
}