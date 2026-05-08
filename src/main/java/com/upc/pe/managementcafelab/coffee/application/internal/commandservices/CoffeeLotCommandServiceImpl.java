package com.upc.pe.managementcafelab.coffee.application.internal.commandservices;

import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.CreateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.DeleteCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.services.CoffeeLotCommandService;
import com.upc.pe.managementcafelab.coffee.insfrastructure.persistence.jpa.repositories.CoffeeLotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CoffeeLotCommandServiceImpl implements CoffeeLotCommandService {

    private final CoffeeLotRepository repository;

    public CoffeeLotCommandServiceImpl(CoffeeLotRepository repository) {
        this.repository = repository;
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
}