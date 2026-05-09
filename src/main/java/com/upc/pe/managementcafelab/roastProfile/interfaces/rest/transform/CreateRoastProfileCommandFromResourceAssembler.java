package com.upc.pe.managementcafelab.roastProfile.interfaces.rest.transform;

import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.CreateRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.resources.CreateRoastProfileResource;

public class CreateRoastProfileCommandFromResourceAssembler {

    public static CreateRoastProfileCommand toCommand(
            CreateRoastProfileResource resource
    ) {

        return new CreateRoastProfileCommand(
                resource.coffeeLotId(),
                resource.userId(),
                resource.name(),
                resource.temperatureStart(),
                resource.temperatureEnd(),
                resource.durationSeconds(),
                resource.type(),
                resource.isFavorite()
        );
    }
}