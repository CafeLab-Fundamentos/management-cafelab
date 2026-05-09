package com.upc.pe.managementcafelab.coffee.domain.model.commands;

public record DeleteCoffeeLotCommand(
        Long coffeeLotId
) {
    public DeleteCoffeeLotCommand {
        if (coffeeLotId == null || coffeeLotId <= 0)
            throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");
    }
}