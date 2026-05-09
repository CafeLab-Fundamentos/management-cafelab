package com.upc.pe.managementcafelab.supplier.domain.model.queries;

import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;

public record GetSuppliersBySpecialityQuery(
        SupplierSpeciality speciality
) {
}