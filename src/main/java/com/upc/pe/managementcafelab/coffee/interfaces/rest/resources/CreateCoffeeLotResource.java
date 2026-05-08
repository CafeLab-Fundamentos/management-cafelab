package com.upc.pe.managementcafelab.coffee.interfaces.rest.resources;

import java.util.List;

public record CreateCoffeeLotResource(
        Long coffeeLotId,
        Long supplierId,
        Long userId,
        String lotName,
        String coffeeType,
        String origin,
        Double altitudeMeters,
        String status,
        Double initialWeight,
        String processingMethod,
        List<String> certifications
) {

    public CreateCoffeeLotResource {

        if (coffeeLotId == null || coffeeLotId <= 0) {
            throw new IllegalArgumentException("coffeeLotId is required and must be positive");
        }

        if (supplierId == null || supplierId <= 0) {
            throw new IllegalArgumentException("supplierId is required and must be positive");
        }

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId is required and must be positive");
        }

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

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }

        if (initialWeight == null || initialWeight < 0) {
            throw new IllegalArgumentException("initialWeight must be >= 0");
        }

        if (processingMethod == null || processingMethod.isBlank()) {
            throw new IllegalArgumentException("processingMethod is required");
        }
    }
}