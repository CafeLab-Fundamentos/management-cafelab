package com.upc.pe.managementcafelab.coffee.interfaces.rest.resources;

import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.Certification;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.LotStatus;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.ProcessingMethod;

import java.util.List;

public record CoffeeLotResource(
        Long coffeeLotId,
        Long supplierId,
        Long userId,
        String lotName,
        String coffeeType,
        String origin,
        Double altitudeMeters,
        String status,
        Double remainingWeight,
        String processingMethod,
        List<String> certifications
) {
}