package com.upc.pe.managementcafelab.inventory.interfaces.rest.transform;

import com.upc.pe.managementcafelab.inventory.domain.model.aggregates.InventoryEntry;
import com.upc.pe.managementcafelab.inventory.interfaces.rest.resources.InventoryEntryResource;

public class InventoryEntryResourceFromEntityAssembler {

    public static InventoryEntryResource toResourceFromEntity(InventoryEntry entity) {
        return new InventoryEntryResource(
                entity.getId(),
                entity.getUserId(),
                entity.getCoffeeLotId(),
                entity.getQuantityUsed(),
                entity.getDateUsed().toString(),
                entity.getFinalProduct()
        );
    }
}
