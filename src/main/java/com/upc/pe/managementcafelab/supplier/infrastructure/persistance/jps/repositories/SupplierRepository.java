package com.upc.pe.managementcafelab.supplier.infrastructure.persistance.jps.repositories;

import com.upc.pe.managementcafelab.supplier.domain.model.aggregates.Supplier;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByUserId(Long userId);

    List<Supplier> findByStatus(SupplierStatus status);

    List<Supplier> findBySpecialitiesContaining(SupplierSpeciality speciality);

    boolean existsByEmail_Value(String value);
}