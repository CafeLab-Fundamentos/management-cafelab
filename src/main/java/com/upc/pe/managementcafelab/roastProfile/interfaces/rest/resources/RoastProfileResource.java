package com.upc.pe.managementcafelab.roastProfile.interfaces.rest.resources;

public record RoastProfileResource(
        Long roastProfileId,
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
}