package com.upc.pe.managementcafelab.coffee.interfaces.rest.transform;

import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotRemainingWeightCommand;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotRemainingWeightResource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateCoffeeLotRemainingWeightCommandFromResourceAssembler {

    private static final DateTimeFormatter ISO_LOCAL = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static UpdateCoffeeLotRemainingWeightCommand toCommand(
            Long coffeeLotId,
            UpdateCoffeeLotRemainingWeightResource resource
    ) {
        LocalDateTime dateUsed = null;
        if (resource.dateUsed() != null && !resource.dateUsed().isBlank()) {
            String raw = resource.dateUsed().trim();
            if (raw.endsWith("Z")) {
                dateUsed = LocalDateTime.parse(raw.substring(0, raw.length() - 1), ISO_LOCAL);
            } else if (raw.contains("T")) {
                dateUsed = LocalDateTime.parse(raw.length() > 19 ? raw.substring(0, 19) : raw, ISO_LOCAL);
            } else {
                dateUsed = LocalDateTime.parse(raw, ISO_LOCAL);
            }
        }

        String finalProduct = resource.finalProduct() != null
                ? resource.finalProduct().trim()
                : "";

        return new UpdateCoffeeLotRemainingWeightCommand(
                coffeeLotId,
                resource.weight(),
                finalProduct,
                dateUsed
        );
    }
}