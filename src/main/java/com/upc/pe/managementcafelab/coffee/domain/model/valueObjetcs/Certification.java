package com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs;

import java.util.Arrays;
import java.util.List;

public record Certification(String value) {
    private static final List<String> VALID_TYPES = Arrays.asList("Organic", "Fairtrade", "Rainforest alliance", "Utz", "Bird friendly", "4c");

    public Certification {
        if (value.length() > 20) {
            throw new IllegalArgumentException("La certificación no puede tener más de 20 caracteres");
        }
        if (!VALID_TYPES.contains(value)) {
            throw new IllegalArgumentException("La certificacion debe ser de la lista: " + VALID_TYPES);
        }
    }

    public Certification() {
        this(null);
    }
}
