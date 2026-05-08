package com.upc.pe.managementcafelab.coffee.persistence.jpa.repositories;

import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.LotStatus;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.ProcessingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeLotRepository extends JpaRepository<CoffeeLot, Long> {

    List<CoffeeLot> findAll();

    List<CoffeeLot> findByStatus(LotStatus status);

    List<CoffeeLot> findByCoffeeType(CoffeeType coffeeType);

    List<CoffeeLot> findByOrigin(String origin);

    List<CoffeeLot> findByProcessingMethod(
            ProcessingMethod processingMethod
    );

    List<CoffeeLot> findBySupplierId(Long supplierId);

    List<CoffeeLot> findByUserId(Long userId);

    List<CoffeeLot> findByRemainingWeightGreaterThan(
            Double remainingWeight
    );

    List<CoffeeLot> findByRemainingWeightEquals(
            Double remainingWeight
    );
}