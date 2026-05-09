package com.upc.pe.managementcafelab.coffee.domain.services;


import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface CoffeeLotQueryService {

    List<CoffeeLot> handle(GetAllCoffeeLotsQuery query);

    List<CoffeeLot> handle(GetAvailableCoffeeLotsQuery query);

    Optional<CoffeeLot> handle(GetCoffeeLotByIdQuery query);

    List<CoffeeLot> handle(GetCoffeeLotsByCoffeeTypeQuery query);

    List<CoffeeLot> handle(GetCoffeeLotsByOriginQuery query);

    List<CoffeeLot> handle(GetCoffeeLotsByProcessingMethodQuery query);

    List<CoffeeLot> handle(GetCoffeeLotsByStatusQuery query);

    List<CoffeeLot> handle(GetCoffeeLotsBySupplierIdQuery query);

    List<CoffeeLot> handle(GetCoffeeLotsByUserIdQuery query);

    List<CoffeeLot> handle(GetDepletedCoffeeLotsQuery query);

}