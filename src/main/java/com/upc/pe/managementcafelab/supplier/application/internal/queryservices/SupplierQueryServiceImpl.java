package com.upc.pe.managementcafelab.supplier.application.internal.queryservices;

import com.upc.pe.managementcafelab.supplier.domain.model.aggregates.Supplier;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.*;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierStatus;
import com.upc.pe.managementcafelab.supplier.domain.services.SupplierQueryService;
import com.upc.pe.managementcafelab.supplier.infrastructure.persistance.jps.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierQueryServiceImpl implements SupplierQueryService {

    private final SupplierRepository repository;

    public SupplierQueryServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Supplier> handle(GetAllSuppliersQuery query) {
        return repository.findAll();
    }

    @Override
    public Optional<Supplier> handle(GetSupplierByIdQuery query) {
        return repository.findById(query.supplierId());
    }

    @Override
    public List<Supplier> handle(GetSuppliersByUserIdQuery query) {
        return repository.findByUserId(query.userId());
    }

    @Override
    public List<Supplier> handle(GetSuppliersByStatusQuery query) {

        return repository.findByStatus(
                SupplierStatus.valueOf(query.status().toUpperCase())
        );
    }

    @Override
    public List<Supplier> handle(GetSuppliersBySpecialityQuery query) {
        return repository.findBySpecialitiesContaining(query.speciality());
    }
}