package com.upc.pe.managementcafelab.coffee.interfaces.rest.resources;

import java.util.List;

public record UpdateCoffeeLotResource(
        String lotName,
        String coffeeType,
        String origin,
        Double altitudeMeters,
        String processingMethod,
        List<String> certifications
) {

    public UpdateCoffeeLotResource {

        if (lotName == null || lotName.isBlank()) {
            throw new IllegalArgumentException("lotName is required");
        }

        if (coffeeType == null || coffeeType.isBlank()) {
            throw new IllegalArgumentException("coffeeType is required");
        }

        if (origin == null || origin.isBlank()) {
            throw new IllegalArgumentException("origin is required");
        }

        if (altitudeMeters == null || altitudeMeters < 0) {
            throw new IllegalArgumentException("altitudeMeters must be >= 0");
        }

        if (processingMethod == null || processingMethod.isBlank()) {
            throw new IllegalArgumentException("processingMethod is required");
        }
    }
}