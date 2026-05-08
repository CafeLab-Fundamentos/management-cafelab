package com.upc.pe.managementcafelab.coffee.domain.model.commands;

public record UpdateCoffeeLotRemainingWeightCommand(
        Long coffeeLotId,
        Double remainingWeight
) {
}