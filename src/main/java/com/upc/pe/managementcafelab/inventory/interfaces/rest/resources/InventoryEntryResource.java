package com.upc.pe.managementcafelab.inventory.interfaces.rest.resources;

public record InventoryEntryResource(
        Long id,
        Long userId,
        Long coffeeLotId,
        Double quantityUsed,
        String dateUsed,
        String finalProduct
) {
}
