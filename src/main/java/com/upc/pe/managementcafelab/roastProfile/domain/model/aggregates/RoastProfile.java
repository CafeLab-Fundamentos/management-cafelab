package com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates;

import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.CreateRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.UpdateRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.DurationInSeconds;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.RoastType;
import com.upc.pe.managementcafelab.roastProfile.domain.model.valueObjects.TemperatureRange;
import com.upc.pe.managementcafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class RoastProfile extends AuditableAbstractAggregateRoot<RoastProfile> {

    @Column(name = "coffee_lot_id", nullable = false)
    private Long coffeeLotId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private TemperatureRange temperatureRange;

    @Embedded
    private DurationInSeconds durationSeconds;

    @Enumerated(EnumType.STRING)
    @Column(name = "roast_type", nullable = false)
    private RoastType type;

    @Column(name = "is_Favorite", nullable = false)
    private Boolean isFavorite;

    public RoastProfile() {
    }
    public RoastProfile(
            Long coffeeLotId,
            Long userId,
            String name,
            TemperatureRange temperatureRange,
            DurationInSeconds durationSeconds,
            RoastType type,
            Boolean isFavorite
    ) {
        this.coffeeLotId = coffeeLotId;
        this.userId = userId;
        this.name = name;
        this.temperatureRange = temperatureRange;
        this.durationSeconds = durationSeconds;
        this.type = type;
        this.isFavorite = isFavorite;
    }

    public RoastProfile(CreateRoastProfileCommand command) {
        this(
                command.coffeeLotId(),
                command.userId(),
                command.name(),
                new TemperatureRange(command.temperatureStart(), command.temperatureEnd()),
                new DurationInSeconds(command.durationSeconds()),
                RoastType.valueOf(command.type().toUpperCase()),
                command.isFavorite()
        );
    }

    public void update(UpdateRoastProfileCommand command) {
        this.name = command.name();
        this.temperatureRange = new TemperatureRange(command.temperatureStart(), command.temperatureEnd());
        this.durationSeconds = new DurationInSeconds(command.durationSeconds());
        this.type = RoastType.valueOf(command.type().toUpperCase());
        this.isFavorite = command.isFavorite();

    }
}