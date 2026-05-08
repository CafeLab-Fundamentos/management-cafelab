package com.upc.pe.managementcafelab.coffee.domain.services;

import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.*;

import java.util.Optional;

public interface CoffeeLotCommandService {

    Optional<CoffeeLot> handle(CreateCoffeeLotCommand command);

    Optional<CoffeeLot> handle(UpdateCoffeeLotCommand command);

    void handle(DeleteCoffeeLotCommand command);

    Optional<CoffeeLot> handle(UpdateCoffeeLotStatusCommand command);

    Optional<CoffeeLot> handle(UpdateCoffeeLotRemainingWeightCommand command);
}