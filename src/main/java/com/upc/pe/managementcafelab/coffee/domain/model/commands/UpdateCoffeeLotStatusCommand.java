package com.upc.pe.managementcafelab.coffee.domain.model.commands;

public record UpdateCoffeeLotStatusCommand(
        Long coffeeLotId,
        String status
) {
}