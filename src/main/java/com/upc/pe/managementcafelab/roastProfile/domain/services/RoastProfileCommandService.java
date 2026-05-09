package com.upc.pe.managementcafelab.roastProfile.domain.services;

import com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates.RoastProfile;
import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.CreateRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.UpdateRoastProfileCommand;

import java.util.Optional;

public interface RoastProfileCommandService {

    Optional<RoastProfile> handle(CreateRoastProfileCommand command);
    Optional<RoastProfile> handle(UpdateRoastProfileCommand command);
}
