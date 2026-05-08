package com.upc.pe.managementcafelab.coffee.domain.model.queries;

public record GetCoffeeLotsByProcessingMethodQuery(String processingMethod) {
    public GetCoffeeLotsByProcessingMethodQuery {
        if (processingMethod == null || processingMethod.isBlank()) {
            throw new IllegalArgumentException("processingMethod es requerido");
        }
    }
}