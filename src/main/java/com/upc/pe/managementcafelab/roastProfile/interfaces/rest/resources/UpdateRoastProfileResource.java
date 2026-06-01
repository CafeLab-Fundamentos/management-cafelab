    package com.upc.pe.managementcafelab.roastProfile.interfaces.rest.resources;

    public record UpdateRoastProfileResource(
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