package com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs;

import java.util.Arrays;
import java.util.List;

public record LotStatus(String value) {
    ;
    private static final List<String> VALID_TYPES = Arrays.asList("Disponible", "Agotado", "En cuarentena");

    public LotStatus {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El status no puede ser nulo o vacío");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("El status no puede tener más de 50 caracteres");
        }
        if (!VALID_TYPES.contains(value)) {
            throw new IllegalArgumentException("El status debe ser uno de: " + VALID_TYPES);
        }
    }

    public LotStatus() {
        this(null);
    }
}
