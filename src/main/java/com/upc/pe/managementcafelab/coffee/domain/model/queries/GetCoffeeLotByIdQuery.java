package com.upc.pe.managementcafelab.coffee.domain.model.queries;

public record GetCoffeeLotByIdQuery(Long coffeeLotId) {
    public GetCoffeeLotByIdQuery {
        if (coffeeLotId == null || coffeeLotId <= 0) {
            throw new IllegalArgumentException("coffeeLotId es requerido");
        }
    }
}