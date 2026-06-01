package com.upc.pe.managementcafelab.roastProfile.domain.model.commands;

public record CreateRoastProfileCommand(
        Long coffeeLotId,
        Long userId,
        String name,
        Double temperatureStart,
        Double temperatureEnd,
        Integer durationSeconds,
        String type,
        Boolean isFavorite,
        Integer acidity,
        Integer sweetness,
        Integer body
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
        if (acidity == null || acidity < 0 || acidity > 100)
            throw new IllegalArgumentException("acidity must be between 0 and 100");
        if (sweetness == null || sweetness < 0 || sweetness > 100)
            throw new IllegalArgumentException("sweetness must be between 0 and 100");
        if (body == null || body < 0 || body > 100)
            throw new IllegalArgumentException("body must be between 0 and 100");
    }
}