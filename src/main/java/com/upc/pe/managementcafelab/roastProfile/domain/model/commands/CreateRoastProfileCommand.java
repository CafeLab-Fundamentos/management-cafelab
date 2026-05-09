package com.upc.pe.managementcafelab.roastProfile.domain.model.commands;

public record CreateRoastProfileCommand(
        Long coffeeLotId,
        Long userId,
        String name,
        Double temperatureStart,
        Double temperatureEnd,
        Integer durationSeconds,
        String type,
        Boolean isFavorite
) {

    public CreateRoastProfileCommand {

        if (coffeeLotId == null || coffeeLotId <= 0)
            throw new IllegalArgumentException("coffeeLotId is required");

        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("userId is required");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name is required");

        if (temperatureStart == null)
            throw new IllegalArgumentException("temperatureStart is required");

        if (temperatureEnd == null)
            throw new IllegalArgumentException("temperatureEnd is required");

        if (temperatureEnd <= temperatureStart)
            throw new IllegalArgumentException("temperatureEnd must be greater than temperatureStart");

        if (durationSeconds == null || durationSeconds <= 0)
            throw new IllegalArgumentException("durationSeconds must be positive");

        if (type == null || type.isBlank())
            throw new IllegalArgumentException("type is required");

        if (isFavorite == null)
            throw new IllegalArgumentException("isFavorite is required");
    }
}