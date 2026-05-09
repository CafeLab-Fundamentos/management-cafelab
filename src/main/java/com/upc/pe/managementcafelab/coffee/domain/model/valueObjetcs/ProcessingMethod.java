package com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs;

import java.util.Arrays;
import java.util.List;

public record ProcessingMethod(String value) {

    private static final List<String> VALID_TYPES = Arrays.asList("Lavado", "Natural", "Honey", "Fermentación anaeróbica", "Maceración carbónica");

    public ProcessingMethod {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El procesamiento no puede ser nulo o vacío");
        }
        if (value.length() > 30) {
            throw new IllegalArgumentException("El procesamiento no puede tener más de 50 caracteres");
        }
        if (!VALID_TYPES.contains(value)) {
            throw new IllegalArgumentException("El procesamiento debe ser uno de: " + VALID_TYPES);
        }
    }

    public ProcessingMethod() {
        this(null);
    }
}
