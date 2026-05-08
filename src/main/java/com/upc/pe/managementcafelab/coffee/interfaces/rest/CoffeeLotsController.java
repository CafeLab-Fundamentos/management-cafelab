package com.upc.pe.managementcafelab.coffee.interfaces.rest;

import com.upc.pe.managementcafelab.coffee.domain.model.queries.*;
import com.upc.pe.managementcafelab.coffee.domain.services.CoffeeLotCommandService;
import com.upc.pe.managementcafelab.coffee.domain.services.CoffeeLotQueryService;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.CreateCoffeeLotResource;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotRemainingWeightResource;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotResource;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.resources.UpdateCoffeeLotStatusResource;
import com.upc.pe.managementcafelab.coffee.interfaces.rest.transform.*;
import com.upc.pe.managementcafelab.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/api/v1/coffee-lots",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Coffee Lots", description = "Coffee lot management endpoints")
public class CoffeeLotsController {

    private final CoffeeLotCommandService commandService;
    private final CoffeeLotQueryService queryService;

    public CoffeeLotsController(
            CoffeeLotCommandService commandService,
            CoffeeLotQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create coffee lot")
    public ResponseEntity<?> create(
            @RequestBody CreateCoffeeLotResource resource
    ) {

        try {

            var command =
                    CreateCoffeeLotCommandFromResourceAssembler
                            .toCommand(resource);

            var created =
                    commandService.handle(command);

            if (created.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new MessageResource("Could not create coffee lot"));
            }

            return new ResponseEntity<>(
                    CoffeeLotResourceFromEntityAssembler
                            .toResourceFromEntity(created.get()),
                    HttpStatus.CREATED
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all coffee lots")
    public ResponseEntity<?> getAll() {

        var lots =
                queryService.handle(new GetAllCoffeeLotsQuery());

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{coffeeLotId}")
    @Operation(summary = "Get coffee lot by id")
    public ResponseEntity<?> getById(
            @PathVariable Long coffeeLotId
    ) {

        var lot =
                queryService.handle(
                        new GetCoffeeLotByIdQuery(coffeeLotId)
                );

        if (lot.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResource("Coffee lot not found"));
        }

        return ResponseEntity.ok(
                CoffeeLotResourceFromEntityAssembler
                        .toResourceFromEntity(lot.get())
        );
    }

    @GetMapping("/available")
    @Operation(summary = "Get available coffee lots")
    public ResponseEntity<?> getAvailable() {

        var lots =
                queryService.handle(new GetAvailableCoffeeLotsQuery());

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/depleted")
    @Operation(summary = "Get depleted coffee lots")
    public ResponseEntity<?> getDepleted() {

        var lots =
                queryService.handle(new GetDepletedCoffeeLotsQuery());

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get coffee lots by status")
    public ResponseEntity<?> getByStatus(
            @PathVariable String status
    ) {

        var lots =
                queryService.handle(
                        new GetCoffeeLotsByStatusQuery(status)
                );

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/origin/{origin}")
    @Operation(summary = "Get coffee lots by origin")
    public ResponseEntity<?> getByOrigin(
            @PathVariable String origin
    ) {

        var lots =
                queryService.handle(
                        new GetCoffeeLotsByOriginQuery(origin)
                );

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/coffee-type/{coffeeType}")
    @Operation(summary = "Get coffee lots by coffee type")
    public ResponseEntity<?> getByCoffeeType(
            @PathVariable String coffeeType
    ) {

        var lots =
                queryService.handle(
                        new GetCoffeeLotsByCoffeeTypeQuery(coffeeType)
                );

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/processing-method/{processingMethod}")
    @Operation(summary = "Get coffee lots by processing method")
    public ResponseEntity<?> getByProcessingMethod(
            @PathVariable String processingMethod
    ) {

        var lots =
                queryService.handle(
                        new GetCoffeeLotsByProcessingMethodQuery(
                                processingMethod
                        )
                );

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get coffee lots by supplier")
    public ResponseEntity<?> getBySupplier(
            @PathVariable Long supplierId
    ) {

        var lots =
                queryService.handle(
                        new GetCoffeeLotsBySupplierIdQuery(
                                supplierId
                        )
                );

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get coffee lots by user")
    public ResponseEntity<?> getByUser(
            @PathVariable Long userId
    ) {

        var lots =
                queryService.handle(
                        new GetCoffeeLotsByUserIdQuery(userId)
                );

        var resources =
                lots.stream()
                        .map(CoffeeLotResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @PutMapping(
            value = "/{coffeeLotId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update coffee lot")
    public ResponseEntity<?> update(
            @PathVariable Long coffeeLotId,
            @RequestBody UpdateCoffeeLotResource resource
    ) {

        try {

            var command =
                    UpdateCoffeeLotCommandFromResourceAssembler
                            .toCommand(coffeeLotId, resource);

            var updated =
                    commandService.handle(command);

            if (updated.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MessageResource("Coffee lot not found"));
            }

            return ResponseEntity.ok(
                    CoffeeLotResourceFromEntityAssembler
                            .toResourceFromEntity(updated.get())
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @PatchMapping(
            value = "/{coffeeLotId}/status",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update coffee lot status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long coffeeLotId,
            @RequestBody UpdateCoffeeLotStatusResource resource
    ) {

        try {

            var command =
                    UpdateCoffeeLotStatusCommandFromResourceAssembler
                            .toCommand(coffeeLotId, resource);

            var updated =
                    commandService.handle(command);

            if (updated.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MessageResource("Coffee lot not found"));
            }

            return ResponseEntity.ok(
                    CoffeeLotResourceFromEntityAssembler
                            .toResourceFromEntity(updated.get())
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @PatchMapping(
            value = "/{coffeeLotId}/consume-stock",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Consume coffee lot stock")
    public ResponseEntity<?> consumeStock(
            @PathVariable Long coffeeLotId,
            @RequestBody UpdateCoffeeLotRemainingWeightResource resource
    ) {

        try {

            var command =
                    UpdateCoffeeLotRemainingWeightCommandFromResourceAssembler
                            .toCommand(coffeeLotId, resource);

            var updated =
                    commandService.handle(command);

            if (updated.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MessageResource("Coffee lot not found"));
            }

            return ResponseEntity.ok(
                    CoffeeLotResourceFromEntityAssembler
                            .toResourceFromEntity(updated.get())
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @DeleteMapping("/{coffeeLotId}")
    @Operation(summary = "Delete coffee lot")
    public ResponseEntity<?> delete(
            @PathVariable Long coffeeLotId
    ) {

        commandService.handle(
                new com.upc.pe.managementcafelab.coffee.domain.model.commands.DeleteCoffeeLotCommand(
                        coffeeLotId
                )
        );

        return ResponseEntity.ok(
                new MessageResource("Coffee lot deleted")
        );
    }
}