package com.upc.pe.managementcafelab.supplier.interfaces.rest.resources;

import java.util.List;

public record SupplierResource(
        Long supplierId,
        Long userId,
        String name,
        String email,
        String phone,
        String location,
        String status,
        List<String> specialities
) {
}