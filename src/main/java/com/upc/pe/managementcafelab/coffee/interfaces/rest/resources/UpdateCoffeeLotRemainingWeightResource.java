package com.upc.pe.managementcafelab.coffee.interfaces.rest.resources;

public record UpdateCoffeeLotRemainingWeightResource(
        Double weight,
        String finalProduct,
        String dateUsed
) {

    public UpdateCoffeeLotRemainingWeightResource {

        if (weight == null || weight <= 0) {
            throw new IllegalArgumentException("weight is required");
        }
    }
}