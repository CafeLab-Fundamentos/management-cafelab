package com.upc.pe.managementcafelab.coffee.domain.model.commands;

import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;

public record UpdateCoffeeLotCommand(
        Long coffeeLotId,
        Long supplierId,
        Long userId,
        String lotName,
        CoffeeType coffeeType,
        String origin,
        String status,
        Double altitudeMeters,
        String processingMethod,
        Double initialWeight
) {
    public UpdateCoffeeLotCommand {
        if (coffeeLotId == null || coffeeLotId <= 0)
            throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");

        if (supplierId == null || supplierId <= 0)
            throw new IllegalArgumentException("SupplierId es requerido y debe ser positivo");

        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("UserId es requerido y debe ser positivo");

        if (lotName == null || lotName.isBlank())
            throw new IllegalArgumentException("LotName es requerido");

        if (coffeeType == null)
            throw new IllegalArgumentException("CoffeeType es requerido");

        if (origin == null || origin.isBlank())
            throw new IllegalArgumentException("Origin es requerido");

        if (status == null || status.isBlank())
            throw new IllegalArgumentException("Status es requerido");

        if (altitudeMeters == null || altitudeMeters <= 0)
            throw new IllegalArgumentException("AltitudeMeters es requerido y debe ser positivo");

        if (processingMethod == null || processingMethod.isBlank())
            throw new IllegalArgumentException("ProcessingMethod es requerido");

        if (initialWeight == null || initialWeight <= 0)
            throw new IllegalArgumentException("InitialWeight es requerido y debe ser positivo");
    }
}