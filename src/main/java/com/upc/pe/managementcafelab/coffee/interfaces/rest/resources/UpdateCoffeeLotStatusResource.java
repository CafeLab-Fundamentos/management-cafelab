package com.upc.pe.managementcafelab.coffee.interfaces.rest.resources;

public record UpdateCoffeeLotStatusResource(
        String status
) {

    public UpdateCoffeeLotStatusResource {

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }
    }
}