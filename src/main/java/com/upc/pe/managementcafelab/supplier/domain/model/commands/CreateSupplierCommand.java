package com.upc.pe.managementcafelab.supplier.domain.model.commands;

import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;

import java.util.List;

public record CreateSupplierCommand(
        Long userId,
        String name,
        String email,
        String phone,
        String location,
        String status,
        List<SupplierSpeciality> specialities
) {

    public CreateSupplierCommand {

        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("UserId es requerido y debe ser positivo");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name es requerido");

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email es requerido");

        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone es requerido");

        if (location == null || location.isBlank())
            throw new IllegalArgumentException("Location es requerido");

        if (status == null || status.isBlank())
            throw new IllegalArgumentException("Status es requerido");

        if (specialities == null)
            throw new IllegalArgumentException("Specialities es requerido");
    }
}