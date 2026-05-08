package com.upc.pe.managementcafelab.coffee.interfaces.rest.transform;

import com.upc.pe.managementcafelab.coffee.domain.model.commands.CreateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.LotStatus;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.ProcessingMethod;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.CreateCoffeeLotResource;

public class CreateCoffeeLotCommandFromResourceAssembler {

    public static CreateCoffeeLotCommand toCommand(CreateCoffeeLotResource resource) {

        return new CreateCoffeeLotCommand(
                resource.coffeeLotId(),
                resource.supplierId(),
                resource.userId(),
                resource.lotName(),
                new CoffeeType(resource.coffeeType()),
                resource.origin(),
                new LotStatus(resource.status()),
                resource.altitudeMeters(),
                new ProcessingMethod(resource.processingMethod()),
                resource.initialWeight()
        );
    }
}