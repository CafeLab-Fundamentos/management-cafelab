package com.upc.pe.managementcafelab.coffee.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateCoffeeLotRemainingWeightCommand(
        Long coffeeLotId,
        Double remainingWeight,
        String finalProduct,
        LocalDateTime dateUsed
) {
}