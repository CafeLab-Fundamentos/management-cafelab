package com.upc.pe.managementcafelab.roastProfile.interfaces.rest;

import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.DeleteRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetAllRoastProfilesQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetFavoriteRoastProfilesQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetRoastProfileByIdQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetRoastProfilesByCoffeeLotIdQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.services.RoastProfileCommandService;
import com.upc.pe.managementcafelab.roastProfile.domain.services.RoastProfileQueryService;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.resources.CreateRoastProfileResource;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.resources.UpdateRoastProfileResource;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.transform.CreateRoastProfileCommandFromResourceAssembler;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.transform.RoastProfileResourceFromEntityAssembler;
import com.upc.pe.managementcafelab.roastProfile.interfaces.rest.transform.UpdateRoastProfileCommandFromResourceAssembler;
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
        value = "/api/v1/roast-profiles",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Roast Profiles", description = "Roast profile management endpoints")
public class RoastProfilesController {

    private final RoastProfileCommandService commandService;
    private final RoastProfileQueryService queryService;

    public RoastProfilesController(
            RoastProfileCommandService commandService,
            RoastProfileQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create roast profile")
    public ResponseEntity<?> create(
            @RequestBody CreateRoastProfileResource resource
    ) {

        try {

            var command =
                    CreateRoastProfileCommandFromResourceAssembler
                            .toCommand(resource);

            var created =
                    commandService.handle(command);

            if (created.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new MessageResource("Could not create roast profile"));
            }

            return new ResponseEntity<>(
                    RoastProfileResourceFromEntityAssembler
                            .toResourceFromEntity(created.get()),
                    HttpStatus.CREATED
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all roast profiles")
    public ResponseEntity<?> getAll() {

        var roastProfiles =
                queryService.handle(new GetAllRoastProfilesQuery());

        var resources =
                roastProfiles.stream()
                        .map(RoastProfileResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{roastProfileId}")
    @Operation(summary = "Get roast profile by id")
    public ResponseEntity<?> getById(
            @PathVariable Long roastProfileId
    ) {

        var roastProfile =
                queryService.handle(
                        new GetRoastProfileByIdQuery(roastProfileId)
                );

        if (roastProfile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResource("Roast profile not found"));
        }

        return ResponseEntity.ok(
                RoastProfileResourceFromEntityAssembler
                        .toResourceFromEntity(roastProfile.get())
        );
    }

    @GetMapping("/coffee-lot/{coffeeLotId}")
    @Operation(summary = "Get roast profiles by coffee lot id")
    public ResponseEntity<?> getByCoffeeLotId(
            @PathVariable Long coffeeLotId
    ) {

        var roastProfiles =
                queryService.handle(
                        new GetRoastProfilesByCoffeeLotIdQuery(coffeeLotId)
                );

        var resources =
                roastProfiles.stream()
                        .map(RoastProfileResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/favorites")
    @Operation(summary = "Get favorite roast profiles")
    public ResponseEntity<?> getFavorites() {

        var roastProfiles =
                queryService.handle(
                        new GetFavoriteRoastProfilesQuery(Boolean.TRUE)
                );

        var resources =
                roastProfiles.stream()
                        .map(RoastProfileResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @PutMapping(
            value = "/{roastProfileId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update roast profile")
    public ResponseEntity<?> update(
            @PathVariable Long roastProfileId,
            @RequestBody UpdateRoastProfileResource resource
    ) {

        try {

            var command =
                    UpdateRoastProfileCommandFromResourceAssembler
                            .toCommand(roastProfileId, resource);

            var updated =
                    commandService.handle(command);

            if (updated.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MessageResource("Roast profile not found"));
            }

            return ResponseEntity.ok(
                    RoastProfileResourceFromEntityAssembler
                            .toResourceFromEntity(updated.get())
            );

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.badRequest()
                    .body(new MessageResource(ex.getMessage()));
        }
    }

    @DeleteMapping("/{roastProfileId}")
    @Operation(summary = "Delete roast profile")
    public ResponseEntity<?> delete(
            @PathVariable Long roastProfileId
    ) {

        commandService.handle(
                new DeleteRoastProfileCommand(roastProfileId)
        );

        return ResponseEntity.ok(
                new MessageResource("Roast profile deleted")
        );
    }
}