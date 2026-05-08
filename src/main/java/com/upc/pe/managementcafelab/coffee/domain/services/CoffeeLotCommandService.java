package com.upc.pe.managementcafelab.coffee.domain.services;

import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.CreateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.DeleteCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotCommand;

import java.util.Optional;

public interface CoffeeLotCommandService {

    Optional<CoffeeLot> handle(CreateCoffeeLotCommand command);

    Optional<CoffeeLot> handle(UpdateCoffeeLotCommand command);

    void handle(DeleteCoffeeLotCommand command);
}