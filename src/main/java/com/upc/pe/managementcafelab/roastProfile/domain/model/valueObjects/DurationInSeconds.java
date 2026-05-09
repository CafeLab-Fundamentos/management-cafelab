package com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects;

public record DurationInSeconds (Integer seconds){
    public DurationInSeconds {
        if (seconds <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor que 0");
        }}



    public DurationInSeconds() {
        this(null);
    }
}
