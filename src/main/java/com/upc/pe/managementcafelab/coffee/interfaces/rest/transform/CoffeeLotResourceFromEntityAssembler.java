package com.upc.pe.managementcafelab.coffee.interfaces.rest.transform;


import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.Certification;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.LotStatus;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.ProcessingMethod;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.CoffeeLotResource;

import java.util.List;
import java.util.stream.Collectors;

public class CoffeeLotResourceFromEntityAssembler {

    public static CoffeeLotResource toResourceFromEntity(CoffeeLot entity) {

        return new CoffeeLotResource(
                entity.getCoffeeLotId(),
                entity.getSupplierId(),
                entity.getUserId(),
                entity.getLotName(),
                entity.getCoffeeType().toString(),
                entity.getOrigin(),
                entity.getAltitudeMeters(),
                entity.getStatus().toString(),
                entity.getRemainingWeight(),
                entity.getProcessingMethod().toString(),
                entity.getCertifications()
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.toList())
        );
    }
}