package com.upc.pe.managementcafelab.inventory.domain.model.aggregates;

import com.upc.pe.managementcafelab.inventory.domain.model.commands.CreateInventoryEntryCommand;
import com.upc.pe.managementcafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class InventoryEntry extends AuditableAbstractAggregateRoot<InventoryEntry> {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "coffee_lot_id", nullable = false)
    private Long coffeeLotId;

    @Column(name = "quantity_used", nullable = false)
    private Double quantityUsed;

    @Column(name = "date_used", nullable = false)
    private LocalDateTime dateUsed;

    @Column(name = "final_product", nullable = false)
    private String finalProduct;

    public InventoryEntry() {
    }

    public InventoryEntry(
            Long userId,
            Long coffeeLotId,
            Double quantityUsed,
            LocalDateTime dateUsed,
            String finalProduct
    ) {
        this.userId = userId;
        this.coffeeLotId = coffeeLotId;
        this.quantityUsed = quantityUsed;
        this.dateUsed = dateUsed;
        this.finalProduct = finalProduct;
    }

    public InventoryEntry(CreateInventoryEntryCommand command) {
        this(
                command.userId(),
                command.coffeeLotId(),
                command.quantityUsed(),
                command.dateUsed(),
                command.finalProduct()
        );
    }
}
