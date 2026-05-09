package com.upc.pe.managementcafelab.roastProfile.application.internal.commandservices;

import com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates.RoastProfile;
import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.CreateRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.DeleteRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.model.commands.UpdateRoastProfileCommand;
import com.upc.pe.managementcafelab.roastProfile.domain.services.RoastProfileCommandService;
import com.upc.pe.managementcafelab.roastProfile.infrastructure.persistence.jpa.repositories.RoastProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoastProfileCommandServiceImpl implements RoastProfileCommandService {

    private final RoastProfileRepository repository;

    public RoastProfileCommandServiceImpl(RoastProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Optional<RoastProfile> handle(CreateRoastProfileCommand command) {

        var roastProfile = new RoastProfile(command);

        return Optional.of(repository.save(roastProfile));
    }

    @Override
    @Transactional
    public Optional<RoastProfile> handle(UpdateRoastProfileCommand command) {

        return repository.findById(command.roastProfileId())
                .map(existingRoastProfile -> {

                    existingRoastProfile.update(command);

                    return repository.save(existingRoastProfile);
                });
    }

    @Override
    @Transactional
    public void handle(DeleteRoastProfileCommand command) {

        repository.findById(command.roastProfileId())
                .ifPresent(repository::delete);
    }
}