package com.upc.pe.managementcafelab.coffee.domain.exceptions;

public class InvalidNewStatusException extends RuntimeException {
    public InvalidNewStatusException() {
        super("No se puede marcar como 'Disponible' a un lote sin peso en (kg)");
    }
}