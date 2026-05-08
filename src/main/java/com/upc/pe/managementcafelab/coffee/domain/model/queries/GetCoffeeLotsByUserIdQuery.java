package com.upc.pe.managementcafelab.coffee.domain.model.queries;

public record GetCoffeeLotsByUserIdQuery(Long userId) {
    public GetCoffeeLotsByUserIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId es requerido");
        }
    }
}