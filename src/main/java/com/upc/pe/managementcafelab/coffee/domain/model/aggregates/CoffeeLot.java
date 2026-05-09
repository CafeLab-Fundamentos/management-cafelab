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
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class CoffeeLot extends AuditableAbstractAggregateRoot<CoffeeLot> {

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "lot_name", nullable = false)
    private String lotName;


    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "altitude_meters", nullable = false)
    private Double altitudeMeters;


    @Column(name = "remaining_weight", nullable = false)
    private Double remainingWeight;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "coffee_type", nullable = false)
    )
    private CoffeeType coffeeType;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "status", nullable = false)
    )
    private LotStatus status;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "processing_method", nullable = false)
    )
    private ProcessingMethod processingMethod;

    @ElementCollection(targetClass = Certification.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "coffee_lot_certifications",
            joinColumns = @JoinColumn(name = "coffee_lot_id")
    )
    @Column(name = "certification")
    private List<Certification> certifications = new ArrayList<>();

    private CoffeeLot(
            Long supplierId,
            Long userId,
            String lotName,
            String coffeeType,
            String origin,
            String status,
            Double altitudeMeters,
            String processingMethod,
            Double initialWeight,
            List<Certification> certifications
    ) {
        this.supplierId = supplierId;
        this.userId = userId;
        this.lotName = lotName;
        this.coffeeType = new CoffeeType(coffeeType);
        this.origin = origin;
        this.altitudeMeters = altitudeMeters;
        this.remainingWeight = initialWeight;
        this.status = new LotStatus(status);
        this.processingMethod = new ProcessingMethod(processingMethod);

        this.certifications = certifications != null
                ? certifications
                : new ArrayList<>();
    }

    public CoffeeLot(CreateCoffeeLotCommand command) {
        this(
                command.supplierId(),
                command.userId(),
                command.lotName(),
                command.coffeeType(),
                command.origin(),
                command.status(),
                command.altitudeMeters(),
                command.processingMethod(),
                command.initialWeight(),
                command.certifications()
        );
    }

    public CoffeeLot() {

    }

    public void applyUpdate(UpdateCoffeeLotCommand command) {
        this.lotName = command.lotName();
        this.coffeeType = command.coffeeType();
        this.origin = command.origin();
        this.altitudeMeters = command.altitudeMeters();
        this.processingMethod = command.processingMethod();
        this.certifications = command.certifications();
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
