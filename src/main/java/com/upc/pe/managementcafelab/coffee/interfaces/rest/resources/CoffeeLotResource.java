package com.upc.pe.managementcafelab.coffee.interfaces.rest.resources;

import java.util.List;

public record CoffeeLotResource(
        Long id,
        Long coffeeLotId,
        Long supplierId,
        Long userId,
        String lotName,
        String coffeeType,
        String origin,
        Double altitudeMeters,
        String status,
        Double remainingWeight,
        String processingMethod,
        List<String> certifications
) {
}