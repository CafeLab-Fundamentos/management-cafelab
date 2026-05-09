package com.upc.pe.managementcafelab.roastProfile.domain.model.commands;

import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.DurationInSeconds;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.RoastType;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.TemperatureRange;

public record UpdateRoastProfileCommand(
        Long roastProfileId,
        String name,
        Double temperatureStart,
        Double temperatureEnd,
        Integer durationSeconds,
        String type,
        Boolean isFavorite
) {

    public UpdateRoastProfileCommand {

        if (roastProfileId == null || roastProfileId <= 0)
            throw new IllegalArgumentException("roastProfileId is required");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name is required");

        if (temperatureEnd == null)
            throw new IllegalArgumentException("temperatureEnd is required");
        if (temperatureStart == null)
            throw new IllegalArgumentException("temperatureStart is required");

        if (durationSeconds == null)
            throw new IllegalArgumentException("durationSeconds is required");

        if (type == null)
            throw new IllegalArgumentException("type is required");

        if (isFavorite == null)
            throw new IllegalArgumentException("isFavorite is required");
    }
}