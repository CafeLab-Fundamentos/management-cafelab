package com.upc.pe.managementcafelab.supplier.interfaces.rest.resources;

import java.util.List;

public record UpdateSupplierResource(
        String name,
        String email,
        String phone,
        String location,
        String status,
        List<String> specialities
) {

    public UpdateSupplierResource {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }

        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("phone is required");
        }

        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("location is required");
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }

        if (specialities == null) {
            throw new IllegalArgumentException("specialities is required");
        }
    }
}