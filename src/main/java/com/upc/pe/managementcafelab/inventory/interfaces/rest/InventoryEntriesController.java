package com.upc.pe.managementcafelab.inventory.interfaces.rest;

import com.upc.pe.managementcafelab.inventory.domain.model.queries.GetInventoryEntriesByUserIdQuery;
import com.upc.pe.managementcafelab.inventory.domain.services.InventoryEntryQueryService;
import com.upc.pe.managementcafelab.inventory.interfaces.rest.transform.InventoryEntryResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/api/v1/inventory-entries",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Inventory Entries", description = "Stock consumption history endpoints")
public class InventoryEntriesController {

    private final InventoryEntryQueryService queryService;

    public InventoryEntriesController(InventoryEntryQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get inventory entries by user")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId) {
        var entries = queryService.handle(new GetInventoryEntriesByUserIdQuery(userId));

        var resources = entries.stream()
                .map(InventoryEntryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }
}
