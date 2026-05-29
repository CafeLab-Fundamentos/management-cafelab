package com.upc.pe.managementcafelab.coffee.application.internal.commandservices;

import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.*;
import com.upc.pe.managementcafelab.coffee.domain.services.CoffeeLotCommandService;
import com.upc.pe.managementcafelab.coffee.insfrastructure.persistence.jpa.repositories.CoffeeLotRepository;
import com.upc.pe.managementcafelab.inventory.domain.model.commands.CreateInventoryEntryCommand;
import com.upc.pe.managementcafelab.inventory.domain.services.InventoryEntryCommandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CoffeeLotCommandServiceImpl implements CoffeeLotCommandService {

    private final CoffeeLotRepository repository;
    private final InventoryEntryCommandService inventoryEntryCommandService;

    public CoffeeLotCommandServiceImpl(
            CoffeeLotRepository repository,
            InventoryEntryCommandService inventoryEntryCommandService
    ) {
        this.repository = repository;
        this.inventoryEntryCommandService = inventoryEntryCommandService;
    }

    @Override
    @Transactional
    public Optional<CoffeeLot> handle(CreateCoffeeLotCommand command) {
        var coffeeLot = new CoffeeLot(command);
        return Optional.of(repository.save(coffeeLot));
    }

    @Override
    @Transactional
    public Optional<CoffeeLot> handle(UpdateCoffeeLotCommand command) {
        return repository.findById(command.coffeeLotId())
                .map(existingCoffeeLot -> {
                    existingCoffeeLot.applyUpdate(command);
                    return repository.save(existingCoffeeLot);
                });
    }

    @Override
    @Transactional
    public void handle(DeleteCoffeeLotCommand command) {
        repository.findById(command.coffeeLotId())
                .ifPresent(repository::delete);
    }

    @Override
    @Transactional
    public Optional<CoffeeLot> handle(UpdateCoffeeLotStatusCommand command) {

        return repository.findById(command.coffeeLotId())
                .map(coffeeLot -> {

                    coffeeLot.advanceStatus(command.status());

                    return repository.save(coffeeLot);
                });
    }

    @Override
    @Transactional
    public Optional<CoffeeLot> handle(UpdateCoffeeLotRemainingWeightCommand command) {

        return repository.findById(command.coffeeLotId())
                .map(coffeeLot -> {

                    coffeeLot.consumeStock(command.remainingWeight());

                    var saved = repository.save(coffeeLot);

                    inventoryEntryCommandService.handle(
                            new CreateInventoryEntryCommand(
                                    coffeeLot.getUserId(),
                                    command.coffeeLotId(),
                                    command.remainingWeight(),
                                    command.dateUsed() != null
                                            ? command.dateUsed()
                                            : LocalDateTime.now(),
                                    command.finalProduct() != null
                                            ? command.finalProduct()
                                            : ""
                            )
                    );

                    return saved;
                });
    }
}