package com.upc.pe.managementcafelab.coffee.domain.model.aggregates;

import com.upc.pe.managementcafelab.coffee.domain.exceptions.InvalidNewStatusException;
import com.upc.pe.managementcafelab.coffee.domain.exceptions.LotInvariantException;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.CreateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.UpdateCoffeeLotCommand;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.Certification;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.CoffeeType;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.LotStatus;
import com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs.ProcessingMethod;
import com.upc.pe.managementcafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class CoffeeLot extends AuditableAbstractAggregateRoot<CoffeeLot> {

    @Column(name = "coffee_lot_id", nullable = false)
    private Long coffeeLotId;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "lot_name", nullable = false)
    private String lotName;

    @Column(name = "coffee_type", nullable = false)
    private CoffeeType coffeeType;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "altitude_meters", nullable = false)
    private Double altitudeMeters;

    @Column(name = "status", nullable = false)
    private LotStatus status;

    @Column(name = "remaining_weight", nullable = false)
    private Double remainingWeight;

    @Column(name = "processing_method", nullable = false)
    private ProcessingMethod processingMethod;

    @Column(name = "certification", nullable = false)
    private List<Certification> certifications;

    private CoffeeLot(
            Long coffeeLotId,
            Long supplierId,
            Long userId,
            String lotName,
            CoffeeType coffeeType,
            String origin,
            String status,
            Double altitudeMeters,
            String processingMethod,
            Double initialWeight
    ) {
        this.coffeeLotId = coffeeLotId;
        this.supplierId         = supplierId;
        this.userId             = userId;
        this.lotName            = lotName;
        this.coffeeType         = coffeeType;
        this.origin             = origin;
        this.altitudeMeters     = altitudeMeters;
        this.remainingWeight    = initialWeight;
        this.status             = new LotStatus(status);
        this.processingMethod  = new ProcessingMethod(processingMethod);
        this.certifications     = new ArrayList<>();
    }

    public CoffeeLot(CreateCoffeeLotCommand command) {
        this(
                command.coffeeLotId(),
                command.supplierId(),
                command.userId(),
                command.lotName(),
                command.coffeeType(),
                command.origin(),
                command.status(),
                command.altitudeMeters(),
                command.processingMethod(),
                command.initialWeight()
        );
    }

    public void applyUpdate(UpdateCoffeeLotCommand command) {
        this.lotName = command.lotName();
        this.coffeeType = command.coffeeType();
        this.origin = command.origin();
        this.altitudeMeters = command.altitudeMeters();
        this.processingMethod = new ProcessingMethod(command.processingMethod());
    }

    public void advanceStatus(String targetStatus) {

        LotStatus newStatus = new LotStatus(targetStatus);
        if (Objects.equals(targetStatus, "Disponible")&& this.remainingWeight<=0) {
            throw new InvalidNewStatusException();
        }
        LotStatus previous = this.status;
        this.status = newStatus;

    }

    public void consumeStock(double quantity) {

        if (!Objects.equals(this.status, new LotStatus("Disponible"))) {
            throw new LotInvariantException();

        }

        if (this.remainingWeight - quantity < 0) {
            this.remainingWeight = this.remainingWeight - quantity;
        }

        // agotado cuando es 0
        if (this.remainingWeight == 0) {
            this.status = new LotStatus("Agotado");
        }
    }

}
