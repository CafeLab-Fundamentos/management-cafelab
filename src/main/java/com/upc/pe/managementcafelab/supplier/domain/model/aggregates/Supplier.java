package com.upc.pe.managementcafelab.supplier.domain.model.aggregates;

import com.upc.pe.managementcafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.CreateSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.UpdateSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.Email;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Supplier extends AuditableAbstractAggregateRoot<Supplier> {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Email email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "location", nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SupplierStatus status;

    @ElementCollection(targetClass = SupplierSpeciality.class)
    @CollectionTable(
            name = "supplier_specialities",
            joinColumns = @JoinColumn(name = "supplier_id")
    )
    @Column(name = "speciality")
    private List<SupplierSpeciality> specialities = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(
            Long userId,
            String name,
            Email email,
            String phone,
            String location,
            SupplierStatus status,
            List<SupplierSpeciality> specialities
    ) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.status = status;
        this.specialities = specialities != null
                ? specialities
                : new ArrayList<>();
    }

    public Supplier(CreateSupplierCommand command) {
        this(
                command.userId(),
                command.name(),
                new Email(command.email()),
                command.phone(),
                command.location(),
                SupplierStatus.valueOf(command.status()),
                command.specialities()
        );
    }

    public void update(UpdateSupplierCommand command) {
        this.name = command.name();
        this.email = new Email(command.email());
        this.phone = command.phone();
        this.location = command.location();
        this.status = SupplierStatus.valueOf(command.status());
        this.specialities = command.specialities();
    }
}