package com.upc.pe.managementcafelab.roastProfile.interfaces.rest.transform;

import com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates.RoastProfile;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.resources.RoastProfileResource;

public class RoastProfileResourceFromEntityAssembler {

    public static RoastProfileResource toResourceFromEntity(
            RoastProfile entity
    ) {

        return new RoastProfileResource(
                entity.getId(),
                entity.getCoffeeLotId(),
                entity.getUserId(),
                entity.getName(),
                entity.getTemperatureRange().start(),
                entity.getTemperatureRange().end(),
                entity.getDurationSeconds().seconds(),
                entity.getType().name(),
                entity.getIsFavorite()
        );
    }
}