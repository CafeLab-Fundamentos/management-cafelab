package com.upc.pe.managementcafelab.supplier.domain.services;

import com.upc.pe.managementcafelab.supplier.domain.model.aggregates.Supplier;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetAllSuppliersQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSupplierByIdQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSuppliersBySpecialityQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSuppliersByStatusQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSuppliersByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface SupplierQueryService {

    List<Supplier> handle(GetAllSuppliersQuery query);

    Optional<Supplier> handle(GetSupplierByIdQuery query);

    List<Supplier> handle(GetSuppliersByUserIdQuery query);

    List<Supplier> handle(GetSuppliersByStatusQuery query);

    List<Supplier> handle(GetSuppliersBySpecialityQuery query);
}
