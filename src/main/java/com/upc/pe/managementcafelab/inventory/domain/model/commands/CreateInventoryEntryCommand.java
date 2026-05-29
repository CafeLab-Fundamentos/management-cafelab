package com.upc.pe.managementcafelab.inventory.domain.model.commands;

import java.time.LocalDateTime;

public record CreateInventoryEntryCommand(
        Long userId,
        Long coffeeLotId,
        Double quantityUsed,
        LocalDateTime dateUsed,
        String finalProduct
) {
}
