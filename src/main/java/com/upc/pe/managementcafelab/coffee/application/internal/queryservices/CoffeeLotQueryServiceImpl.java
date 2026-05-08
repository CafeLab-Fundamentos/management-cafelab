package com.upc.pe.managementcafelab.coffee.application.internal.queryservices;

import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.queries.*;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.LotStatus;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.ProcessingMethod;
import com.upc.pe.managementcafelab.coffee.domain.services.CoffeeLotQueryService;
import com.upc.pe.managementcafelab.coffee.persistence.jpa.repositories.CoffeeLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeLotQueryServiceImpl implements CoffeeLotQueryService {

    private final CoffeeLotRepository repository;

    public CoffeeLotQueryServiceImpl(CoffeeLotRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CoffeeLot> handle(GetAllCoffeeLotsQuery query) {
        return repository.findAll();
    }

    @Override
    public List<CoffeeLot> handle(GetAvailableCoffeeLotsQuery query) {
        return repository.findByRemainingWeightGreaterThan(0.0);
    }

    @Override
    public Optional<CoffeeLot> handle(GetCoffeeLotByIdQuery query) {
        return repository.findById(query.coffeeLotId());
    }

    @Override
    public List<CoffeeLot> handle(GetCoffeeLotsByCoffeeTypeQuery query) {
        return repository.findByCoffeeType(
                new CoffeeType(query.coffeeType())
        );
    }

    @Override
    public List<CoffeeLot> handle(GetCoffeeLotsByOriginQuery query) {
        return repository.findByOrigin(
                query.origin()
        );
    }

    @Override
    public List<CoffeeLot> handle(GetCoffeeLotsByProcessingMethodQuery query) {
        return repository.findByProcessingMethod(
                new ProcessingMethod(query.processingMethod())
        );
    }

    @Override
    public List<CoffeeLot> handle(GetCoffeeLotsByStatusQuery query) {
        return repository.findByStatus(
                new LotStatus(query.status())
        );
    }

    @Override
    public List<CoffeeLot> handle(GetCoffeeLotsBySupplierIdQuery query) {
        return repository.findBySupplierId(
                query.supplierId()
        );
    }

    @Override
    public List<CoffeeLot> handle(GetCoffeeLotsByUserIdQuery query) {
        return repository.findByUserId(
                query.userId()
        );
    }

    @Override
    public List<CoffeeLot> handle(GetDepletedCoffeeLotsQuery query) {
        return repository.findByRemainingWeightEquals(0.0);
    }
}