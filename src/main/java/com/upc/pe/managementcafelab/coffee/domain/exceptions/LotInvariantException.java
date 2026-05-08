package com.upc.pe.managementcafelab.coffee.domain.exceptions;

public class LotInvariantException extends RuntimeException {
    public LotInvariantException() {
        super("No se puede sacar kg. de un lote que no esta disponible");
    }
}
