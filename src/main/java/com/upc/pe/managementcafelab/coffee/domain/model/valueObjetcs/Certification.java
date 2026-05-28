package com.upc.pe.managementcafelab.coffee.domain.model.valueObjetcs;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Certification {
    ORGANIC,
    FAIR_TRADE,
    RAINFOREST;

    private static final String ALLOWED = Arrays.stream(values())
            .map(Enum::name)
            .collect(Collectors.joining(", "));

    /**
     * Convierte el texto del API al enum. Acepta nombres como {@code FAIR_TRADE} o {@code fair_trade}.
     */
    public static Certification from(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("La certificación no puede ser nula o vacía");
        }
        String normalized = raw.trim().toUpperCase().replace('-', '_').replace(' ', '_');
        try {
            return Certification.valueOf(normalized);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                    "Certificación inválida: '" + raw.trim() + "'. Valores permitidos: " + ALLOWED
            );
        }
    }
}