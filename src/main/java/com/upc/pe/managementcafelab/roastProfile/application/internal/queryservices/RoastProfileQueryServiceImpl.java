package com.upc.pe.managementcafelab.roastProfile.application.internal.queryservices;

import com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates.RoastProfile;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetAllRoastProfilesQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetFavoriteRoastProfilesQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetRoastProfileByIdQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetRoastProfilesByCoffeeLotIdQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetRoastProfilesByUserIdQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.services.RoastProfileQueryService;
import com.upc.pe.managementcafelab.roastProfile.infrastructure.persistence.jpa.repositories.RoastProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoastProfileQueryServiceImpl implements RoastProfileQueryService {

    private final RoastProfileRepository repository;

    public RoastProfileQueryServiceImpl(RoastProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoastProfile> handle(GetAllRoastProfilesQuery query) {

        return repository.findAll();
    }

    @Override
    public Optional<RoastProfile> handle(GetRoastProfileByIdQuery query) {

        return repository.findById(query.roastProfileId());
    }

    @Override
    public List<RoastProfile> handle(GetRoastProfilesByCoffeeLotIdQuery query) {

        return repository.findByCoffeeLotId(query.coffeeLotId());
    }

    @Override
    public List<RoastProfile> handle(GetRoastProfilesByUserIdQuery query) {

        return repository.findByUserId(query.userId());
    }

    @Override
    public List<RoastProfile> handle(GetFavoriteRoastProfilesQuery query) {

        return repository.findByIsFavorite(query.isFavorite());
    }
}