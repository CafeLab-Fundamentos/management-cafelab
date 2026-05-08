package com.upc.pe.managementcafelab.coffee.domain.model.queries;

public record GetCoffeeLotsByStatusQuery(String status) {
    public GetCoffeeLotsByStatusQuery {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status es requerido");
        }
    }
}