package com.upc.pe.managementcafelab.coffee.domain.model.queries;

import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;

public record GetCoffeeLotsByCoffeeTypeQuery(CoffeeType coffeeType) {
    public GetCoffeeLotsByCoffeeTypeQuery {
        if (coffeeType == null) {
            throw new IllegalArgumentException("coffeeType es requerido");
        }
    }
}