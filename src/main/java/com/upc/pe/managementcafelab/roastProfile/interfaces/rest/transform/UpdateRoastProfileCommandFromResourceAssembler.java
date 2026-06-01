package com.upc.pe.managementcafelab.roastProfile.interfaces.rest.transform;

import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.UpdateRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.DurationInSeconds;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.RoastType;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.TemperatureRange;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.resources.UpdateRoastProfileResource;

public class UpdateRoastProfileCommandFromResourceAssembler {

    public static UpdateRoastProfileCommand toCommand(
            Long roastProfileId,
            UpdateRoastProfileResource resource
    ) {

        return new UpdateRoastProfileCommand(
                roastProfileId,
                resource.name(),
                resource.temperatureStart(),
                resource.temperatureEnd(),
                resource.durationSeconds(),
                resource.type().toUpperCase(),
                resource.isFavorite(),
                resource.acidity(),
                resource.sweetness(),
                resource.body()
        );
    }
}