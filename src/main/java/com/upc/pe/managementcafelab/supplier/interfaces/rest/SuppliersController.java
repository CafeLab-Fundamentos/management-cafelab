package com.upc.pe.managementcafelab.supplier.interfaces.rest;

import com.upc.pe.managementcafelab.shared.interfaces.rest.resources.MessageResource;
import com.upc.pe.managementcafelab.supplier.domain.model.commands.DeleteSupplierCommand;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetAllSuppliersQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSupplierByIdQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSuppliersBySpecialityQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSuppliersByStatusQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.queries.GetSuppliersByUserIdQuery;
import com.upc.pe.managementcafelab.supplier.domain.model.valueObjects.SupplierSpeciality;
import com.upc.pe.managementcafelab.supplier.domain.services.SupplierCommandService;
import com.upc.pe.managementcafelab.supplier.domain.services.SupplierQueryService;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.resources.CreateSupplierResource;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.resources.UpdateSupplierResource;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.transform.CreateSupplierCommandFromResourceAssembler;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.transform.SupplierResourceFromEntityAssembler;
import com.upc.pe.managementcafelab.supplier.interfaces.rest.transform.UpdateSupplierCommandFromResourceAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/api/v1/suppliers",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Suppliers", description = "Supplier management endpoints")
public class SuppliersController {

    private final SupplierCommandService commandService;
    private final SupplierQueryService queryService;

    public SuppliersController(
            SupplierCommandService commandService,
            SupplierQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create supplier")
    public ResponseEntity<?> create(
            @RequestBody CreateSupplierResource resource
    ) {

        try {

            var command =
                    CreateSupplierCommandFromResourceAssembler
                            .toCommandFromResource(resource);

            var created =
                    commandService.handle(command);

            if (created.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new MessageResource("Could not create supplier"));
            }

            return new ResponseEntity<>(
                    SupplierResourceFromEntityAssembler
                            .toResourceFromEntity(created.get()),
                    HttpStatus.CREATED
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all suppliers")
    public ResponseEntity<?> getAll() {

        var suppliers =
                queryService.handle(new GetAllSuppliersQuery());

        var resources =
                suppliers.stream()
                        .map(SupplierResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{supplierId}")
    @Operation(summary = "Get supplier by id")
    public ResponseEntity<?> getById(
            @PathVariable Long supplierId
    ) {

        var supplier =
                queryService.handle(
                        new GetSupplierByIdQuery(supplierId)
                );

        if (supplier.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResource("Supplier not found"));
        }

        return ResponseEntity.ok(
                SupplierResourceFromEntityAssembler
                        .toResourceFromEntity(supplier.get())
        );
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get suppliers by user")
    public ResponseEntity<?> getByUserId(
            @PathVariable Long userId
    ) {

        var suppliers =
                queryService.handle(
                        new GetSuppliersByUserIdQuery(userId)
                );

        var resources =
                suppliers.stream()
                        .map(SupplierResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get suppliers by status")
    public ResponseEntity<?> getByStatus(
            @PathVariable String status
    ) {

        var suppliers =
                queryService.handle(
                        new GetSuppliersByStatusQuery(status)
                );

        var resources =
                suppliers.stream()
                        .map(SupplierResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/speciality/{speciality}")
    @Operation(summary = "Get suppliers by speciality")
    public ResponseEntity<?> getBySpeciality(
            @PathVariable String speciality
    ) {

        var suppliers =
                queryService.handle(
                        new GetSuppliersBySpecialityQuery(
                                new SupplierSpeciality(speciality)
                        )
                );

        var resources =
                suppliers.stream()
                        .map(SupplierResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @PutMapping(
            value = "/{supplierId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update supplier")
    public ResponseEntity<?> update(
            @PathVariable Long supplierId,
            @RequestBody UpdateSupplierResource resource
    ) {

        try {

            var command =
                    UpdateSupplierCommandFromResourceAssembler
                            .toCommandFromResource(supplierId, resource);

            var updated =
                    commandService.handle(command);

            if (updated.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MessageResource("Supplier not found"));
            }

            return ResponseEntity.ok(
                    SupplierResourceFromEntityAssembler
                            .toResourceFromEntity(updated.get())
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @DeleteMapping("/{supplierId}")
    @Operation(summary = "Delete supplier")
    public ResponseEntity<?> delete(
            @PathVariable Long supplierId
    ) {

        commandService.handle(
                new DeleteSupplierCommand(supplierId)
        );

        return ResponseEntity.ok(
                new MessageResource("Supplier deleted")
        );
    }
}