package com.upc.pe.managementcafelab.roastProfile.domain.services;

import com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates.RoastProfile;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface RoastProfileQueryService {

    List<RoastProfile> handle(GetAllRoastProfilesQuery query);

    Optional<RoastProfile> handle(GetRoastProfileByIdQuery query);

    List<RoastProfile> handle(GetRoastProfilesByCoffeeLotIdQuery query);

    List<RoastProfile> handle(GetRoastProfilesByUserIdQuery query);

    List<RoastProfile> handle(GetFavoriteRoastProfilesQuery query);
}