package com.upc.pe.managementcafelab.supplier.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public record Email(String value) {

    public Email {
        Objects.requireNonNull(value, "email must not be null");
        value = value.strip().toLowerCase();
        if (value.isBlank()) {
            throw new IllegalArgumentException("email must not be blank");
        }
        if (!value.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("invalid email format: " + value);
        }
    }

    public Email() {
        this(null);
    }

}