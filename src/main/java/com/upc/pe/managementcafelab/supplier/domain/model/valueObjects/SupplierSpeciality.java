package com.upc.pe.managementcafelab.supplier.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public record SupplierSpeciality(String value) {

    public SupplierSpeciality {

        Objects.requireNonNull(value, "speciality must not be null");

        value = value.strip();

        if (value.isBlank()) {
            throw new IllegalArgumentException("speciality must not be blank");
        }
    }

    public SupplierSpeciality() {
        this(null);
    }
}