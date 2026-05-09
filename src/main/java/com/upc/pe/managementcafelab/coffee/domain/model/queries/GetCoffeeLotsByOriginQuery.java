package com.upc.pe.managementcafelab.coffee.domain.model.queries;

public record GetCoffeeLotsByOriginQuery(String origin) {
    public GetCoffeeLotsByOriginQuery {
        if (origin == null || origin.isBlank()) {
            throw new IllegalArgumentException("origin es requerido");
        }
    }
}