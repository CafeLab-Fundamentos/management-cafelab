package com.upc.pe.managementcafelab.roastProfile.domain.model.commands;

public record DeleteRoastProfileCommand(
        Long roastProfileId
) {
    public DeleteRoastProfileCommand {
        if (roastProfileId == null || roastProfileId <= 0)
            throw new IllegalArgumentException("roastProfileId es requerido y debe ser positivo");
    }
}